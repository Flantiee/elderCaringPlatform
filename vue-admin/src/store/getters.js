const getters = {
  sidebar: state => state.app.sidebar,
  device: state => state.app.device,
  token: state => state.user.token,
  avatar: state => state.user.avatar,
  name: state => state.user.name,
  community: state => state.user.community, //社区名字，别改
  adminID: state => state.user.adminID
}
export default getters
