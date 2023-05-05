import request from "@/utils/request";

export function getLineChartList() {
  return request({
    url: "/dashboard/line",
    method: "get",
  });
}

export function getBarChartList() {
  return request({
    url: "/dashboard/bar",
    method: "get",

  });
}

export function getPieChartList() {
  return request({
    url: "/dashboard/pie",
    method: "get",
 
  });
}

export function getRaddarChartList() {
  return request({
    url: "/dashboard/raddar",
    method: "get",
    
  });
}
