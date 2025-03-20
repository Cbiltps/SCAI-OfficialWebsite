package com.example.scaiofficialwebsite.demos.manager;

import com.example.scaiofficialwebsite.demos.exception.BusinessException;
import com.example.scaiofficialwebsite.demos.exception.ErrorCode;
import lombok.extern.slf4j.Slf4j;
import org.apache.logging.log4j.util.TriConsumer;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.function.Function;

/**
 * Created with IntelliJ IDEA.
 * Description:
 * User: lichengxiang
 * Date: 2025-03-19
 * Time: 10:54
 */
@Service
@Slf4j
public class FileManager {
    @Value("${local.path}")
    String SAVE_PATH;

    public String uploadFileToLocal(MultipartFile multipartFile) {
        String fileNameAndType = multipartFile.getOriginalFilename(); // 获取文件名及类型(xxx.xxx)
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        String time = sdf.format(new Date());
        String savePath = SAVE_PATH + "/" + time + "-" +  fileNameAndType; // 拼接文件路径
        System.out.println("保存文件: " + savePath);
        File destFile = new File(savePath);
        if (!destFile.exists()) {
            destFile.mkdir();
        }
        try {
            multipartFile.transferTo(destFile);
            return savePath;
        } catch (IOException e) {
            log.error("文件上传失败!", e);
            throw new BusinessException(ErrorCode.SYSTEM_ERROR, "文件上传失败!");
        }
    }

//    public void deleteLocalFile(Projects oldProjects) {
//        String deleteFilePath = oldProjects.getImageUrl();
//        System.out.println("删除文件: " + deleteFilePath);
//        File file = new File(deleteFilePath);
//        file.delete();
//    }

    public <T> void deleteLocalFile(T param, Function<T, String> pathExtractor) {
        String deleteFilePath = pathExtractor.apply(param);
        System.out.println("删除文件: " + deleteFilePath);
        File file = new File(deleteFilePath);
        boolean isDeleted = file.delete();
        if (!isDeleted) {
            System.err.println("文件删除失败: " + deleteFilePath);
            throw new BusinessException(ErrorCode.SYSTEM_ERROR, "文件删除失败!");
        }
    }

//    public byte[] readFile(Projects projects) {
//        File file = new File(projects.getImageUrl());
//        byte[] fileContent = null;
//        try {
//            fileContent = Files.readAllBytes(file.toPath());
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//        return fileContent;
//    }

    public <T> byte[] readFile(T param, Function<T, String> pathExtractor) {
        String filePath = pathExtractor.apply(param);
        File file = new File(filePath);
        byte[] fileContent = null;
        try {
            fileContent = Files.readAllBytes(file.toPath());
        } catch (IOException e) {
            log.error("文件读取失败!", e);
            throw new BusinessException(ErrorCode.SYSTEM_ERROR, "文件读取失败!");
        }
        return fileContent;
    }

//    public List<byte[]> readFiles(List<Projects> projectsList) {
//        List<byte[]> allFileContents = new ArrayList<>();
//        for (Projects projects : projectsList) {
//            File file = new File(projects.getImageUrl());
//            byte[] fileContent = null;
//            try {
//                fileContent = Files.readAllBytes(file.toPath());
//            } catch (IOException e) {
//                e.printStackTrace();
//            }
//            if (fileContent != null) {
//                allFileContents.add(fileContent);
//            }
//        }
//        return allFileContents;
//    }
//

    public <T> Map<Long, byte[]> readAllFile(List<T> params, Function<T, String> pathExtractor, Function<T, Long> idExtractor) {
        Map<Long, byte[]> allFileContents = new HashMap<>();
        for (T param : params) {
            String filePath = pathExtractor.apply(param);
            Long id = idExtractor.apply(param);
            File file = new File(filePath);
            System.out.println("需要读取的文件路径: " + filePath);
            byte[] fileContent = null;
            try {
                fileContent = Files.readAllBytes(file.toPath());
            } catch (IOException e) {
                log.error("文件" + id + "读取失败!", e);
                throw new BusinessException(ErrorCode.SYSTEM_ERROR, "文件读取失败!");
            }
            if (fileContent != null) {
                allFileContents.put(id, fileContent);
            }
        }
        return allFileContents;
    }

//    public List<Project> assignFileContentToList(List<Project> projects) {
//        Map<Long, byte[]> allFileContents = readAllFile(projects, Project::getImageUrl, Project::getId);
//        for (Project project : projects) {
//            Long id = project.getId();
//            byte[] fileContent = allFileContents.get(id);
//            if (fileContent != null) {
//                project.setFileContent(fileContent);
//            }
//        }
//        return projects;
//    }

    public <T> List<T> assignFileContentToList(List<T> list, Function<T, String> pathExtractor, Function<T, Long> idExtractor, TriConsumer<T, Long, byte[]> contentSetter) {
        Map<Long, byte[]> allFileContents = readAllFile(list, pathExtractor, idExtractor);
        for (T item : list) {
            Long id = idExtractor.apply(item);
            byte[] fileContent = allFileContents.get(id);
            if (fileContent != null) {
                contentSetter.accept(item, id, fileContent);
            }
        }
        return list;
    }
}
