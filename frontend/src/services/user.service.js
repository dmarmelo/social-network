import ApiService from './api.service'
import TokenService from './storage.service'
import moment from "moment";

class ApplicationError extends Error {
  constructor(errorCode, message) {
    super()
    this.name = this.constructor.name
    this.message = message
    this.errorCode = errorCode
  }
}

class AuthenticationError extends ApplicationError {
  constructor(errorCode, message) {
    super(errorCode, message)
  }
}

class InvalidEmailError extends ApplicationError {
  constructor(errorCode, message) {
    super(errorCode, message)
  }
}

const UserService = {
  /**
   * Login the user and store the access token to TokenService.
   *
   * @returns access_token
   * @throws AuthenticationError
   **/
  login: async function (email, password) {
    try {
      const response = await ApiService.post(
        "/auth/signin",
        {
          usernameOrEmail: email,
          password: password
        }
      )

      TokenService.saveToken(response.data.accessToken)
      ApiService.setHeader()

      ApiService.mount401Interceptor();

      return response.data.accessToken
    } catch (error) {
      throw new AuthenticationError(error.response.status, error.response.data.message)
    }
  },

  /**
   * Signup the user
   */
  signup: async function (name, username, email, password, birthDateStr) {
    try {
      let birthDate = moment(birthDateStr).format('YYYY-MM-DD[T]HH:mm:ss.SSS[Z]')

      const response = await ApiService.post(
        "/auth/signup",
        {
          name: name,
          username: username,
          email: email,
          password: password,
          birthDate: birthDate
        }
      )

      return response.data
    } catch (error) {
      throw new AuthenticationError(error.response.status, error.response.data.message)
    }
  },

  checkUsernameAvailability: async function (username) {
    const response = await ApiService.get("/api/user/checkUsernameAvailability/" + username)
    return response.data
  },

  checkEmailAvailability: async function (email) {
    try {
      const response = await ApiService.get("/api/user/checkEmailAvailability/" + email)
      return response.data
    } catch (error) {
      throw new InvalidEmailError(error.response.status, error.response.data.message)
    }
  },

  /**
   * Refresh the access token.
   **/
  refreshToken: async function () {

    const requestData = {
      method: 'post',
      url: "/auth/refreshToken"
    }

    try {
      const response = await ApiService.customRequest(requestData)

      TokenService.saveToken(response.data.accessToken)
      // Update the header in ApiService
      ApiService.setHeader()

      return response.data.accessToken
    } catch (error) {
      throw new AuthenticationError(error.response.status, error.response.data.detail)
    }

  },

  /**
   * Logout the current user by removing the token from storage.
   *
   * Will also remove `Authorization Bearer <token>` header from future requests.
   **/
  logout() {
    // Remove the token and remove Authorization header from Api Service as well
    TokenService.removeToken()
    ApiService.removeHeader()

    ApiService.unmount401Interceptor()
  },

  search: async function (search) {
    const response = await ApiService.get("/api/user/search/" + search)
    return response.data
  },
}

export {UserService, AuthenticationError}
