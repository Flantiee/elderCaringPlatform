
const tokens = {
  admin1: {
    token: 'admin1-token'
  },
  admin2: {
    token: 'admin2-token'
  }
}

const users = {
  'admin1-token': {
    roles: ['admin'],
    introduction: 'I am a admin1',
    community:'幸福小区',
    avatar: 'https://wpimg.wallstcn.com/f778738c-e4f8-4870-b634-56703b4acafe.gif',
    name: 'Admin1'
  },
  'admin2-token': {
    roles: ['admin'],
    introduction: 'I am an admin2',
    community:'花园小区',
    avatar: 'https://wpimg.wallstcn.com/f778738c-e4f8-4870-b634-56703b4acafe.gif',
    name: 'Admin2'
  }
}

module.exports = [
  // user login
  {
    url: '/api/user/login',
    type: 'post',
    response: config => {
      const { username } = config.query
      const token = tokens[username]

      // mock error
      if (!token) {
        return {
          code: 207,
          message: 'Account and password are incorrect.'
        }
      }

      return {
        code: 200,
        data: token
      }
    }
  },

  // get user info
  {
    url: '/api/user/info\.*', //无论去哪个页面
    type: 'get',
    response: config => {
      const  {token}  = config.query
      const info = users[token]

      // mock error
      if (!info) {
        return {
          code: 500,
          message: 'Login failed, unable to get user details.'
        }
      }

      return {
        code: 200,
        data: info
      }
    }
  },

  // user logout
  {
    url: '/api/user/logout',
    type: 'post',
    response: _ => {
      return {
        code: 200,
        data: 'success'
      }
    }
  }
]
