import request from '@/utils/request'

export function getElderList() {
  return request({
    url: '/table/elder',
    method: 'get',
  })
}

export function createElder(params) {
  return request({
    url: "/table/elder",
    method: "post",
    params,
  });
}

export function deleteElder(params) {
  return request({
    url: "/table/elder/delete",
    method: "delete",
    params,
  });
}

export function updateElder(params) {
  return request({
    url: "/table/elder/update",
    method: "post",
    params,
  });
}

export function getChildrenList() {
  return request({
    url: '/table/children',
    method: 'get',
  })
}

export function createChildren(params) {
  return request({
    url: "/table/children",
    method: "post",
    params,
  });
}

export function deleteChildren(params) {
  return request({
    url: "/table/children/delete",
    method: "delete",
    params
  });
}

export function updateChildren(params) {
  return request({
    url: "/table/children/update",
    method: "post",
    params,
  });
}

export function getRelationsList() {
  return request({
    url: '/table/relations',
    method: 'get',
  })
}

export function getAvailableList() { //获取可供选择的老人与子女列表
  return request({
    url: '/table/relations/available',
    method: 'get',
  })
}

export function createRelation(params) {
  return request({
    url: "/table/relations",
    method: "post",
    params,
  });
}

export function deleteRelation(params) {
  return request({
    url: "/table/relations/delete",
    method: "delete",
    params,
  });
}

export function updateRelation(params) {
  return request({
    url: "/table/relations/update",
    method: "post",
    params,
  });
}
