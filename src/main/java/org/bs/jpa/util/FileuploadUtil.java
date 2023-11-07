package org.bs.jpa.util;

import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.List;


import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.multipart.MultipartFile;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.Query;
import net.coobird.thumbnailator.Thumbnailator;

@Component
public class FileuploadUtil {

    // 파일이 없을 시 에러
    public static class uploadError extends RuntimeException {

        public uploadError(String msg) {
            super(msg);
        }
    }

    @Value("${org.bs.upload.path}")
    private String path;

    // fileupload
    public List<String> uploadFiles(List<MultipartFile> files) {

        if (files == null || files.size() == 0) {
            throw new uploadError("No File");
        }

        List<String> uploadFnames = new ArrayList<>();

        for (MultipartFile file : files) {

            String originalFileName = file.getOriginalFilename();

            File saveFile = new File(path, originalFileName);

            try (InputStream in = file.getInputStream();
                    OutputStream out = new FileOutputStream(saveFile)) {

                // 원본 카피
                FileCopyUtils.copy(in, out);

                File thumbFile = new File(path + File.separator + "s_" + originalFileName);


                Thumbnailator.createThumbnail(saveFile, thumbFile, 200, 200);

                uploadFnames.add(originalFileName);

            } catch (Exception e) {
                throw new uploadError("upload Error" + e.getMessage());
            }

        }

        return uploadFnames;
    }

    // file Delete
    public void delete(List<String> files) {

        if (files == null || files.size() == 0) {
            return;
        }

        for (String file : files) {

            File original = new File(path, file);

            File thumb = new File(path, "s_" + file);

            if (thumb.exists()) {
                thumb.delete();
            }
            original.delete();
        }
    }

    @PersistenceContext
    private EntityManager entityManager;

    // db file delete
    public void dbFileDelete(String fname){

        try {
            String sql = "DELETE FROM bs_board_file WHERE fname = :fname AND board_bno IS NULL";
            Query query = entityManager.createNativeQuery(sql);
            query.setParameter("fname", fname);
            query.executeUpdate();
            
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

}
