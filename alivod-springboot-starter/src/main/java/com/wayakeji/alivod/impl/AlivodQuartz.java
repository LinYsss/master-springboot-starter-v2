package com.wayakeji.alivod.impl;

import com.aliyuncs.DefaultAcsClient;
import com.aliyuncs.vod.model.v20170321.GetPlayInfoRequest;
import com.aliyuncs.vod.model.v20170321.GetPlayInfoResponse;
import com.aliyuncs.vod.model.v20170321.GetPlayInfoResponse.PlayInfo;
import com.wayakeji.alivod.mapper.PluginAlivodMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Slf4j
@Component
@RequiredArgsConstructor
public class AlivodQuartz {
	
	private boolean canExec = true;

	private final PluginAlivodMapper alivodDao;

	private final Alivod alivod;

	/**
	 * 每隔60秒执行一次
	 */
	@Scheduled(fixedRate = 60000)
	public void updateTranscodeAddr() {
		log.debug("每隔60秒执行一次");
		if(canExec) {
			canExec = false;
			List<String> list = null;
			try {
				list = alivodDao.selectUpdateVideo();
			}catch (Throwable e) {
				e.printStackTrace();
			}
			if(list != null && list.size() > 0) {
				List<Map<String, Object>> params = getAliyunVideo(list);
				if(params.size() > 0) {
					try {
						alivodDao.updateVideoTranscodeAddr(params);
					}catch (Throwable e) {
						e.printStackTrace();
					}
				}
			}
			canExec = true;
		}
	}
	
	public List<Map<String, Object>> getAliyunVideo(List<String> ids) {
		GetPlayInfoRequest request;
		GetPlayInfoResponse resp;
		List<Map<String, Object>> result = new ArrayList<>();
		Map<String, Object> map;
		List<PlayInfo> infos;
		for(int i = 0; i < ids.size(); i++) {
			request = new GetPlayInfoRequest();
			request.setVideoId(ids.get(i));
			try {
				resp = Alivod.client().getAcsResponse(request);
				infos = resp.getPlayInfoList();
				if(infos.size() > 0 && resp.getVideoBase().getCoverURL() != null) {
					map = new HashMap<>();
					map.put("videoId", ids.get(i));
					map.put("address", disposeUrl(infos.get(0).getPlayURL()));
					map.put("cover", resp.getVideoBase().getCoverURL());
					map.put("videoTime", Integer.parseInt(resp.getVideoBase().getDuration().split("\\.")[0]));
					map.put("size", infos.get(0).getSize());
					result.add(map);
				}
			}catch (Exception e) {}
		}
		return result;
	}
	
	private String disposeUrl(String url) {
		if(url.startsWith("https:")) {
			return alivod.getVideoUrlProtocol() + url.substring(5);
		}else if(url.startsWith("http:")) {
			return alivod.getVideoUrlProtocol() + url.substring(4);
		}else {
			return url;
		}
	}
	
}
