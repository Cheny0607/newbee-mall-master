/**
 * 严肃声明：
 * 开源版本请务必保留此注释头信息，若删除我方将保留所有法律责任追究！
 * 本系统已申请软件著作权，受国家版权局知识产权以及国家计算机软件著作权保护！
 * 可正常分享和学习源码，不得用于违法犯罪活动，违者必究！
 * Copyright (c) 2019-2020 十三 all rights reserved.
 * 版权所有，侵权必究！
 */
package ltd.newbee.mall.controller.common;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.text.ParseException;
import javax.annotation.Resource;
import ltd.newbee.mall.common.Constants;
import ltd.newbee.mall.entity.GoodsReview;
import ltd.newbee.mall.entity.GoodsSale;
import ltd.newbee.mall.service.NewBeeMallGoodsService;
import ltd.newbee.mall.util.NewBeeMallUtils;
import ltd.newbee.mall.util.Result;
import ltd.newbee.mall.util.ResultGenerator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.multipart.support.StandardServletMultipartResolver;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * @author 13
 * @qq交流群 796794009
 * @email 2449207463@qq.com
 * @link https://github.com/newbee-ltd
 */
@Controller
@RequestMapping("/admin")
public class UploadController {
    @Autowired
    private StandardServletMultipartResolver standardServletMultipartResolver;
    @Resource
    private NewBeeMallGoodsService newBeeMallGoodsService;

    @PostMapping({"/upload/file"})
    @ResponseBody
    public Result upload(HttpServletRequest httpServletRequest, @RequestParam("file") MultipartFile file) throws URISyntaxException {
        String fileName = file.getOriginalFilename();
        String suffixName = fileName.substring(fileName.lastIndexOf("."));
        //生成文件名称通用方法
        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd_HHmmss");
        Random r = new Random();
        StringBuilder tempName = new StringBuilder();
        tempName.append(sdf.format(new Date())).append(r.nextInt(100)).append(suffixName);
        String newFileName = tempName.toString();
        File fileDirectory = new File(Constants.FILE_UPLOAD_DIC);
        //创建文件
        File destFile = new File(Constants.FILE_UPLOAD_DIC + newFileName);
        try {
            if (!fileDirectory.exists()) {
                if (!fileDirectory.mkdir()) {
                    throw new IOException("文件夹创建失败,路径为：" + fileDirectory);
                }
            }
            file.transferTo(destFile);
            Result resultSuccess = ResultGenerator.genSuccessResult();
            resultSuccess.setData(NewBeeMallUtils.getHost(new URI(httpServletRequest.getRequestURL() + "")) + "/upload/" + newFileName);
            return resultSuccess;
        } catch (IOException e) {
            e.printStackTrace();
            return ResultGenerator.genFailResult("文件上传失败");
        }
    }

    @PostMapping({"/upload/files"})
    @ResponseBody
    public Result uploadV2(HttpServletRequest httpServletRequest) throws URISyntaxException {
        List<MultipartFile> multipartFiles = new ArrayList<>(8);
        if (standardServletMultipartResolver.isMultipart(httpServletRequest)) {
            MultipartHttpServletRequest multiRequest = (MultipartHttpServletRequest) httpServletRequest;
            Iterator<String> iter = multiRequest.getFileNames();
            int total = 0;
            while (iter.hasNext()) {
                if (total > 5) {
                    return ResultGenerator.genFailResult("最多上传5张图片");
                }
                total += 1;
                MultipartFile file = multiRequest.getFile(iter.next());
                multipartFiles.add(file);
            }
        }
        if (CollectionUtils.isEmpty(multipartFiles)) {
            return ResultGenerator.genFailResult("参数异常");
        }
        if (multipartFiles != null && multipartFiles.size() > 5) {
            return ResultGenerator.genFailResult("最多上传5张图片");
        }
        List<String> fileNames = new ArrayList(multipartFiles.size());
        for (int i = 0; i < multipartFiles.size(); i++) {
            String fileName = multipartFiles.get(i).getOriginalFilename();
            String suffixName = fileName.substring(fileName.lastIndexOf("."));
            //生成文件名称通用方法
            SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd_HHmmss");
            Random r = new Random();
            StringBuilder tempName = new StringBuilder();
            tempName.append(sdf.format(new Date())).append(r.nextInt(100)).append(suffixName);
            String newFileName = tempName.toString();
            File fileDirectory = new File(Constants.FILE_UPLOAD_DIC);
            //创建文件
            File destFile = new File(Constants.FILE_UPLOAD_DIC + newFileName);
            try {
                if (!fileDirectory.exists()) {
                    if (!fileDirectory.mkdir()) {
                        throw new IOException("文件夹创建失败,路径为：" + fileDirectory);
                    }
                }
                multipartFiles.get(i).transferTo(destFile);
                fileNames.add(NewBeeMallUtils.getHost(new URI(httpServletRequest.getRequestURL() + "")) + "/upload/" + newFileName);
            } catch (IOException e) {
                e.printStackTrace();
                return ResultGenerator.genFailResult("文件上传失败");
            }
        }
        Result resultSuccess = ResultGenerator.genSuccessResult();
        resultSuccess.setData(fileNames);
        return resultSuccess;
    }

    //added by c 2021/5/13 insert csv
    @PostMapping({"/uploadTest/file"})
    @ResponseBody
    public Result uploadTest(HttpServletRequest httpServletRequest, @RequestParam("file")MultipartFile file)throws URISyntaxException, ParseException {
        try {
            //wrap inputStream
            //bufferedReader
            InputStream is = file.getInputStream();
            BufferedReader br = new BufferedReader(new InputStreamReader(is));
            //while readLine
            String line;
            while((line = br.readLine()) !=null) {
                //split
                String[] arr = line.split(",");
                //set entity
                GoodsSale goodsSale = new GoodsSale();
                goodsSale.setId(Long.parseLong(arr[0]));
                goodsSale.setName(arr[1]);
                SimpleDateFormat sdFormat = new SimpleDateFormat("yyyy/MM/dd");
                goodsSale.setStartDate(sdFormat.parse(arr[2]));
                goodsSale.setEndDate(sdFormat.parse(arr[3]));
                goodsSale.setCampaign(arr[4]);
                //call insert service
                Integer count = null;
                if(goodsSale !=null){
                    count = newBeeMallGoodsService.insertGoodsSale(goodsSale);
                }
                if(!(count > 0)){
                    return ResultGenerator.genFailResult("検索失敗");
                }
                return ResultGenerator.genSuccessResult(count);
            }
            br.close();
        } catch (IOException e) {
            //TODO Auto-generated catch block
            e.printStackTrace();
            return ResultGenerator.genFailResult("文件上传失败");
        }
        Result resultSuccess = ResultGenerator.genSuccessResult();
        return resultSuccess;
    }

    //added by c 2021/5/14 download csv
    @RequestMapping(value = "/download/file",method = RequestMethod.POST)
    @ResponseBody
    public Result downloadFile(@RequestBody Integer[] ids) {
        File f = new File("/Users/chennaiyuan/Desktop/upload/test.csv");
        try {
            BufferedWriter bw = new BufferedWriter(new FileWriter(f,true));
            List<GoodsSale> goodsSaleList = newBeeMallGoodsService.getGoodsSale(ids);
            for(int i = 0;i < goodsSaleList.size();i++){
                GoodsSale goodsSale = goodsSaleList.get(i);
                if(goodsSale !=null) {
                    bw.write(goodsSaleList.get(i).toString());
                    bw.newLine();
                }
            }
           /* goodsSaleList.stream().forEach(goodsSale ->{
                try{
                    bw.write(goodsSale.toString());
                    bw.newLine();
                }catch (IOException e){
                    e.printStackTrace();
                }
            });*/
            bw.close();
        } catch (IOException e) {
            //TODO Auto-generated catch block
            e.printStackTrace();
            return ResultGenerator.genFailResult("文件下载失败");
        }
        Result resultSuccess = ResultGenerator.genSuccessResult("/Users/chennaiyuan/Desktop/upload/test.csv");
        return resultSuccess;
    }

}

