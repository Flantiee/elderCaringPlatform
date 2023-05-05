import request from "@/utils/request";

export function getNewsList() {
  return request({
    url: "/affairs/news",
    method: "get",
  });
}

export function createNews(params) {
  return request({
    url: "/affairs/news",
    method: "post",
    params,
  });
}

export function deleteNews(params) {
  return request({
    url: "/affairs/news/delete",
    method: "delete",
    params
  });
}

export function updateNews(params) {
  return request({
    url: "/affairs/news/update",
    method: "post",
    params,
  });
}


export function getEmergencyList(params) {
  return request({
    url: "/affairs/emergency",
    method: "get",
    params,
  });
}

export function createEmergencyConclude(params) {
  return request({
    url: "/affairs/emergency/conclude",
    method: "post",
    params,
  });
}

export function updateEmergencyStatus(params) {
  return request({
    url: "/affairs/emergency/status",
    method: "post",
    params
  });
}
