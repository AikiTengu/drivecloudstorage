package com.udacity.jwdnd.course1.cloudstorage.mapper;

import com.udacity.jwdnd.course1.cloudstorage.model.Credential;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface CredentialsMapper {

    @Select("SELECT * FROM Credentials WHERE userId = #{userId}")
    List<Credential> getCredential(int userId);

    @Insert("INSERT INTO Credentials (url, username, key, password, userId) VALUES(#{url}, #{userName}, #{key}, #{password}, #{userId})")
    @Options(useGeneratedKeys = true, keyProperty = "credentialId")
    int insert(Credential credential);

    @Update("UPDATE Credentials SET url = #{url}, username = #{userName}, key = #{key}, password = #{password} WHERE credentialId = #{credentialId}")
    int update(Credential credential);

    @Delete("DELETE FROM Credentials WHERE credentialId = #{credentialId}")
    void delete(Integer credentialId);
}