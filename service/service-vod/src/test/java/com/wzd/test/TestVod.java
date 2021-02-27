package com.wzd.test;

import com.aliyun.vod.upload.impl.UploadVideoImpl;
import com.aliyun.vod.upload.req.UploadVideoRequest;
import com.aliyun.vod.upload.resp.UploadVideoResponse;
import com.aliyuncs.DefaultAcsClient;
import com.aliyuncs.exceptions.ClientException;
import com.aliyuncs.vod.model.v20170321.*;
import org.apache.commons.lang.StringUtils;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

/**
 * @ClassName TestVod
 * @Author lofxve
 * @Date 2021/2/26 20:20
 * @Version 1.0
 */
public class TestVod {
    //账号AK信息请填写(必选)
    private static final String accessKeyId = "LTAI4GByaaa6Rpjcr7FD1YyN";
    //账号AK信息请填写(必选)
    private static final String accessKeySecret = "2ORb9i5tjuCxhiOm3IebNFXq00IXe8";

    @Test
    public void PlayAuth() throws ClientException {
        // 初始化对象
        DefaultAcsClient client = InitObject.initVodClient(accessKeyId, accessKeySecret);
        // 创建获取视频地址的请求和响应对象
        GetVideoPlayAuthRequest request = new GetVideoPlayAuthRequest();
        request.setVideoId("65ad4f7e1e7c4b5da45a973bad26f778");
        GetVideoPlayAuthResponse response = new GetVideoPlayAuthResponse();
        try {
            response = client.getAcsResponse(request);
            //播放凭证
            System.out.print("PlayAuth = " + response.getPlayAuth() + "\n");
            //VideoMeta信息
            System.out.print("VideoMeta.Title = " + response.getVideoMeta().getTitle() + "\n");
        } catch (Exception e) {
            System.out.print("ErrorMessage = " + e.getLocalizedMessage());
        }
        System.out.print("RequestId = " + response.getRequestId() + "\n");
    }

    @Test
    public void PlayInfo() throws ClientException {
        // 初始化对象
        DefaultAcsClient client = InitObject.initVodClient(accessKeyId, accessKeySecret);
        // 创建获取视频地址的请求和响应对象
        GetPlayInfoRequest request = new GetPlayInfoRequest();
        request.setVideoId("3a817273ccb743ddb7c190f513450831");
        request.setResultType("Multiple");
        GetPlayInfoResponse response = new GetPlayInfoResponse();
        try {
            response = client.getAcsResponse(request);
            List<GetPlayInfoResponse.PlayInfo> playInfoList = response.getPlayInfoList();
            //播放地址
            for (GetPlayInfoResponse.PlayInfo playInfo : playInfoList) {
                System.out.print("PlayInfo.PlayURL = " + playInfo.getPlayURL() + "\n");
            }
            //Base信息
            System.out.print("VideoBase.Title = " + response.getVideoBase().getTitle() + "\n");
        } catch (Exception e) {
            System.out.print("ErrorMessage = " + e.getLocalizedMessage());
        }
        System.out.print("RequestId = " + response.getRequestId() + "\n");
    }

    /**
     * 视频上传
     */
    @Test
    public void testUploadVideo() {
        //1.音视频上传-本地文件上传
        //视频标题(必选)
        String title = "测试标题";
        //本地文件上传和文件流上传时，文件名称为上传文件绝对路径，如:/User/sample/文件名称.mp4 (必选)
        //文件名必须包含扩展名
        String fileName = "D:\\desktop\\9889e6f103bae7d91279f5d96987d039.mp4";

        UploadVideoRequest request = new UploadVideoRequest(accessKeyId, accessKeySecret, title, fileName);
        /*可指定分片上传时每个分片的大小，默认为1M字节*/
        request.setPartSize(1 * 1024 * 1024L);
        /*可指定分片上传时的并发线程数，默认为1（注：该配置会占用服务器CPU资源，需根据服务器情况指定）*/
        request.setTaskNum(1);
        /*是否开启断点续传，默认断点续传功能关闭。当网络不稳定或者程序崩溃时，再次发起相同上传请求，可以继续未完成的上传任务，适用于超时3000秒仍不能上传完成的大文件。
        注意: 断点续传开启后，会在上传过程中将上传位置写入本地磁盘文件，影响文件上传速度，请您根据实际情况选择是否开启*/
        request.setEnableCheckpoint(false);
        /*OSS慢请求日志打印超时时间，是指每个分片上传时间超过该阈值时会打印debug日志，如果想屏蔽此日志，请调整该阈值。单位: 毫秒，默认为300000毫秒*/
        //request.setSlowRequestsThreshold(300000L);
        /*可指定每个分片慢请求时打印日志的时间阈值，默认为300s*/
        //request.setSlowRequestsThreshold(300000L);
        /*是否使用默认水印（可选），指定模板组ID时，根据模板组配置确定是否使用默认水印*/
        //request.setIsShowWaterMark(true);
        /*自定义消息回调设置（可选），参数说明参考文档 https://help.aliyun.com/document_detail/86952.html#UserData*/
        // request.setUserData("{\"Extend\":{\"test\":\"www\",\"localId\":\"xxxx\"},\"MessageCallback\":{\"CallbackURL\":\"http://test.test.com\"}}");

        /*视频分类ID（可选）*/
        //request.setCateId(0);
        /*视频标签,多个用逗号分隔（可选）*/
        //request.setTags（"标签1,标签2"）;
        /*视频描述（可选）*/
        //request.setDescription("视频描述");
        /*封面图片（可选）*/
        //request.setCoverURL("http://cover.sample.com/sample.jpg");
        /*模板组ID（可选）*/
        //request.setTemplateGroupId("8c4792cbc8694*****d5330e56a33d");
        /*点播服务接入点*/
        //request.setApiRegionId("cn-shanghai");
        /*ECS部署区域，如果与点播存储（OSS）区域相同，则自动使用内网上传文件至存储*/
        // request.setEcsRegionId("cn-shanghai");
        /*存储区域（可选）*/
        //request.setStorageLocation("in-2017032*****18266-5sejdln9o.oss-cn-shanghai.aliyuncs.com");
        /*开启默认上传进度回调*/
        // request.setPrintProgress(true);
        /*设置自定义上传进度回调（必须继承 ProgressListener）*/
        // request.setProgressListener(new PutObjectProgressListener());
        UploadVideoImpl uploader = new UploadVideoImpl();
        UploadVideoResponse response = uploader.uploadVideo(request);
        System.out.print("RequestId="+response.getRequestId()+"\n");  //请求视频点播服务的请求ID
        if (response.isSuccess()) {
            System.out.print("VideoId="+response.getVideoId()+"\n");
        } else {
            /* 如果设置回调URL无效，不影响视频上传，可以返回VideoId同时会返回错误码。其他情况上传失败时，VideoId为空，此时需要根据返回错误码分析具体错误原因 */
            System.out.print("VideoId="+response.getVideoId()+"\n");
            System.out.print("ErrorCode="+response.getCode()+"\n");
            System.out.print("ErrorMessage="+response.getMessage()+"\n");
        }
    }
}
