package com.nl.sgfut.back.controller;

import java.io.File;
import java.net.URI;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.FileSystemResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.nl.sgfut.back.mapper.JsonMapper;
import com.nl.sgfut.back.model.SgfutEvent;
import com.nl.sgfut.back.model.SgfutUser;
import com.nl.sgfut.back.service.SgfutEventService;
import com.nl.sgfut.back.service.SgfutUserService;

@RestController
@RequestMapping("/sample/api")
public class SampleRestApiController {

	@Autowired
	SgfutUserService sgfutUserService;

	@Autowired
	SgfutEventService sgfutEventService;

	private static final Logger log = LoggerFactory.getLogger(SampleRestApiController.class);

	private static final String SLACK_INCOMING_WEBHOOK = "https://hooks.slack.com/services/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";

	@JsonSerialize
	static class SimpleSlackIncoming {
		public String channel = "#sgfut-promotion-team";
		public String username = "test";
		public String text = "SGFUT???????????????????????? Slack ????????????????????????????????????????????????";
		public String icon_emoji = ":ghost:";
	}

	@RequestMapping("/slack/postMessage")
	public String slackPostMessage() {
		SimpleSlackIncoming incoming = new SimpleSlackIncoming();

		// ?????????????????? JSON???????????????
		final ObjectMapper mapper = new ObjectMapper();
		String incomingJson = null;
		try {
			incomingJson = "payload=" + mapper.writeValueAsString(incoming);
		} catch (JsonProcessingException e) {
			e.printStackTrace();
		}

		// API ????????????
		RequestEntity<?> req = RequestEntity //
				.post(URI.create(SLACK_INCOMING_WEBHOOK)) //
				.header("Content-Type", "application/x-www-form-urlencoded; charset=UTF-8") //
				.body(incomingJson);
		new RestTemplate().exchange(req, String.class);

		return "slack??????????????????";
	}

	@RequestMapping("/get/userList")
	public List<SgfutUser> getUserList() {
		
		List<SgfutUser> sgfutUserList = sgfutUserService.findAllSgfutUserData();

		return sgfutUserList;

	}

	@RequestMapping("/get/eventList")
	public List<SgfutEvent> getEventList() {

		List<SgfutEvent> sgfutEventList = sgfutEventService.findAllSgfutEventData();

		return sgfutEventList;

	}

	// ??????????????????????????????
	/******************************************************************************/
	/**
	 * GET http://localhost:8080/sample/api/test/aaa
	 * 
	 * @param param
	 * @return
	 */
	@RequestMapping("/test/{param}")
	private String testPathVariable(@PathVariable String param) {
		log.info(param);		
		return "??????????????????????????????:" + param;
	}

	/**
	 * GET http://localhost:8080/sample/api/test?param=bbb
	 * 
	 * @param param
	 * @return
	 */
	@RequestMapping("/test")
	private String testRequestParam(@RequestParam("param") String param) {
		log.info(param);
		return "??????????????????????????????:" + param;
	}

	/**
	 * POST http://localhost:8080/sample/api/test Body->ccc
	 * 
	 * @param body
	 * @return
	 */
	@RequestMapping(value = "/test", method = RequestMethod.POST)
	private String testRequestBody(@RequestBody String body) {
		log.info(body);
		return "???????????????????????????????????????:" + body;
	}
	/******************************************************************************/

	// HTTP???????????????????????????
	/******************************************************************************/
	/**
	 * POST http://localhost:8080/sample/api/resource Body->aaa
	 * 
	 * @param data
	 * @return
	 */
	@RequestMapping(value = "/resource", method = RequestMethod.POST)
	private String create(@RequestBody String data) {
		return "??????";
	}

	/**
	 * GET http://localhost:8080/sample/api/resource/aaa
	 * 
	 * @param id
	 * @return
	 */
	@RequestMapping(value = "/resource/{id}", method = RequestMethod.GET)
	private String read(@PathVariable String id) {
		return "??????";
	}

	/**
	 * DELETE http://localhost:8080/sample/api/resource/aaa
	 * 
	 * @param id
	 * @return
	 */
	@RequestMapping(value = "/resource/{id}", method = RequestMethod.DELETE)
	private String delete(@PathVariable String id) {
		return "??????";
	}

	/**
	 * PUT http://localhost:8080/sample/api/resource/aaa Body->bbb
	 * 
	 * @param id
	 * @param data
	 * @return
	 */
	@RequestMapping(value = "resource/{id}", method = RequestMethod.PUT)
	private String update(@PathVariable String id, @RequestBody String data) {
		return "??????";
	}
	/******************************************************************************/

	// ????????????JavaBean(POJO)?????????
	/******************************************************************************/
	public static class HogeMogeBean {

		private String hoge;
		private int moge;

		public HogeMogeBean(String string, int i) {
			this.hoge = string;
			this.moge = i;
		}

		public String getHoge() {
			return hoge;
		}

		public void setHoge(String hoge) {
			this.hoge = hoge;
		}

		public int getMoge() {
			return moge;
		}

		public void setMoge(int moge) {
			this.moge = moge;
		}

	}

	/**
	 * GET http://localhost:8080/sample/api/hogemoge
	 * 
	 * @return
	 */
	@RequestMapping("hogemoge")
	private HogeMogeBean hogemoge() {
		return new HogeMogeBean("??????", 1234);
	}
	/******************************************************************************/

	// ????????????Map?????????
	/******************************************************************************/
	/**
	 * GET http://localhost:8080/sample/api/hogemoge3
	 * 
	 * @return
	 */
	@RequestMapping("hogemoge3")
	private Map<String, Object> map() {
		Map<String, Object> map = new HashMap<>();
		map.put("hoge", "??????");
		map.put("moge", 999);
		return map;
	}
	/******************************************************************************/

	// ?????????????????????????????????
	/******************************************************************************/
	/**
	 * GET http://localhost:8080/sample/api/hogemoge4
	 * 
	 * @return
	 */
	@RequestMapping(value = "hogemoge4", produces = MediaType.APPLICATION_OCTET_STREAM_VALUE)
	private Resource file() {
		return new FileSystemResource(new File("C:\\test\\hogemoge.png"));
	}
	/******************************************************************************/

	// ????????????JSON?????????
	/******************************************************************************/
	public static class User {

		private String lastName;
		private String firstName;

		public User(String lastName, String firstName) {
			this.lastName = lastName;
			this.firstName = firstName;
		}

		public String getLastName() {
			return lastName;
		}

		public void setLastName(String lastName) {
			this.lastName = lastName;
		}

		public String getFirstName() {
			return firstName;
		}

		public void setFirstName(String firstName) {
			this.firstName = firstName;
		}
	}

	/**
	 * GET http://localhost:8080/sample/api/json
	 * 
	 * @return
	 */
	@RequestMapping("/json")
	private List<User> json() {
		return Arrays.asList(new User("??????", "??????"), new User("??????", "??????"));
	}
	/******************************************************************************/

	// ?????????????????????
	/******************************************************************************/
	@ExceptionHandler
	private ResponseEntity<String> onError(Exception ex) {

		log.error(ex.getMessage(), ex);

		HttpStatus status = HttpStatus.INTERNAL_SERVER_ERROR;
		String json = JsonMapper.map().put("message", "API ?????????").put("detail", ex.getMessage())
				.put("status", status.value()).stringify();

		return new ResponseEntity<String>(json, status);
	}

	/**
	 * GET http://localhost:8080/sample/api/test/ex
	 * 
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("test/ex")
	private String testException() throws Exception {

		throw new RuntimeException("???????????????");
	}
	/******************************************************************************/
}
