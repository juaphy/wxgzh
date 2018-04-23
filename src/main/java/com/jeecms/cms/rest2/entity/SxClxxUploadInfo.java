package com.jeecms.cms.rest2.entity;

import java.io.Serializable;

/**
 * 调用接口返回的事项材料成功上传后的返回信息
 * @author swc 2018-04-19
 *
 */
public class SxClxxUploadInfo implements Serializable {
    private static final long serialVersionUID = -3004011380737018655L;
    private String fileId; // 材料文件id
    private String filePath; // 材料文件路径
    private String status; // 类型/状态
    private String uploadPath; // 远程上传路径
    public String getUploadPath() {
        return uploadPath;
    }
    public void setUploadPath(String uploadPath) {
        this.uploadPath = uploadPath;
    }
    public String getStatus() {
        return status;
    }
    public void setStatus(String status) {
        this.status = status;
    }
    public String getFileId() {
        return fileId;
    }
    public void setFileId(String fileId) {
        this.fileId = fileId;
    }
    public String getFilePath() {
        return filePath;
    }
    public void setFilePath(String filePath) {
        this.filePath = filePath;
    }

}