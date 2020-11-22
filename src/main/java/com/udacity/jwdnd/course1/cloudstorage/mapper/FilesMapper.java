package com.udacity.jwdnd.course1.cloudstorage.mapper;

import com.udacity.jwdnd.course1.cloudstorage.model.Note;
import com.udacity.jwdnd.course1.cloudstorage.model.sdFile;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface FilesMapper {

    @Select("SELECT * FROM Files WHERE userId = #{userId}")
    List<sdFile> getFiles(int userId);

    @Insert("INSERT INTO Files (filename, contenttype, filesize, userid, filedata) VALUES(#{fileName}, #{contentType}, #{fileSize}, #{userId}, #{fileData})")
    @Options(useGeneratedKeys = true, keyProperty = "fileId")
    int insert(sdFile file);

    @Delete("DELETE FROM Files WHERE fileId = #{fileId}")
    void delete(Integer fileId);
}
