package com.java.spring_restful.controller;
import com.java.spring_restful.DTO.TvseriesDto;
import io.swagger.annotations.Api;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import sun.misc.IOUtils;
import sun.nio.ch.IOUtil;

import java.awt.PageAttributes.MediaType;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.*;

import javax.servlet.http.HttpServletRequest;

@RestController
@RequestMapping("/tvseries")
@Api(tags="TVseries")
public class TvseriesController {

    private static final Log log = LogFactory.getLog(TvseriesController.class);
    @GetMapping
    public List<TvseriesDto> getAll(){
        if(log.isTraceEnabled()){
            log.trace("getAll();被调用了!");
        }
        List<TvseriesDto> list = new ArrayList<>();
		/*
		 * Calendar calendar = Calendar.getInstance();
		 * calendar.set(2016,Calendar.OCTOBER,2,0,0); list.add(new
		 * TvseriesDto(1,"west world",1,calendar.getTime()));
		 */
		/*
		 * calendar.set(2011,Calendar.SEPTEMBER,22,0,0); list.add(new
		 * TvseriesDto(1,"person of Interest",5,calendar.getTime()));
		 */  
        list.add(createPoi());
        list.add(createWestWorld());
        return list;
    }

    @GetMapping("/{id}")
    public TvseriesDto getOne(@PathVariable int id) {
    	if(log.isTraceEnabled()) {
    		log.trace("getone"+ id);
    	}
    	if(id == 101) {
    		return createWestWorld();
    	}else if (id == 102) {
			return createPoi();
		}else {
			//改为异常处理
			/*
			 * 
			 */
			return createWestWorld();
		}
    }
    
    @PostMapping
    public TvseriesDto insertOne(@RequestBody TvseriesDto tvseriesDto) {
    	if(log.isTraceEnabled()) {
    		log.trace("这里应该写新增tvSeriesDto到数据库的代码"+tvseriesDto);
    	}

    	return tvseriesDto;
    }
/*    @PostMapping(value = "/{id}/photos",consumes = MediaType.ISO_DESIGNATED_LONG_ENVELOPE)
	public void addPhoto(@PathVariable int id, @PathVariable("photo")MultipartFile imgFile) throws Exception {
    	if(log.isTraceEnabled()){
    		log.trace("接受到文件" + id + "收到文件" + imgFile.getOriginalFilename());
		}
		FileOutputStream fos = new FileOutputStream("target/" + imgFile.getOriginalFilename());
		try {
			IOUtil.configureBlocking(imgFile.getInputStream(),fos);
		} catch (IOException e) {
			e.printStackTrace();
		}
		try {
			fos.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}*/
    
    @PutMapping("/{id}")
    public TvseriesDto updateOne(@PathVariable int id, @RequestBody TvseriesDto tvseriesDto) {
    	if(log.isTraceEnabled()) {
    		log.trace("updateOne" + id);
    	}

    	if (id == 101) {
			return createPoi();
		} else {
			return createWestWorld();
		}
    }
    @DeleteMapping("/{id}")
/*    public Map<String, String> deleteOne(@PathVariable int id, HttpServletRequest request,
    		@RequestParam(value="delete_reason", required = false) String deleteReason){ */
    public Map<String, String> deleteOne(@PathVariable int id, HttpServletRequest request,
    		@RequestParam(value="delete_reason", required = false) String deleteReason){
    	if(log.isTraceEnabled()) {
    		log.trace("deleteOne" + id);
    	}
		System.out.println(id);
    	Map<String, String> result = new HashMap<>();
    	if(id == 101) {
    		result.put("message", "#101被" + request.getRemoteAddr() + "删除（原因：" + deleteReason + ")");
    	}else if (id == 102) {
			throw new RuntimeException("#102不能被删除");
		}else {
			//不存在
			result.put("error", "ResourcesNotFoundException!");
		}
    	return result;
    }  

	/*
	 * @PostMapping(value = "/{id}/photos",
	 * consumes=MediaType.MULTIPART_FORM_DATA_VaLUe)
	 */
    private TvseriesDto createPoi() {
    	Calendar calendar = Calendar.getInstance();
    	calendar.set(2011, Calendar.SEPTEMBER,22,0,0,0);
    	return new TvseriesDto(102,"Person of Internet",5,calendar.getTime());
    }
    
    private TvseriesDto createWestWorld() {
    	Calendar calendar = Calendar.getInstance();
    	calendar.set(2016, Calendar.OCTOBER,2,0,0,0);
    	return new TvseriesDto(101,"West World",1,calendar.getTime());
    }
}