package com.thoughtworks.capability.gtb.springdatajpaintro;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.stereotype.Component;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.*;
import java.util.concurrent.atomic.AtomicLong;

@Component
public class UserDao {
    public static final String USERS_DATA_FILE_PATH = "/Users/xifwang/tmp/local-filesystem-data-store/users.data";
    final ObjectMapper objectMapper = new ObjectMapper();
    final AtomicLong userIdSeq = new AtomicLong();

    public List<User> findAll() {
        return readFromFile(USERS_DATA_FILE_PATH);
    }

    public Optional<User> findOneById(Long id) {
        return findAll().stream()
                .filter(user -> user.getId().equals(id))
                .findFirst();
    }

    public void save(User user) {
        user.setId(userIdSeq.incrementAndGet());

        List<User> users = findAll();
        users.add(user);

        writeToFile(users, USERS_DATA_FILE_PATH);
    }

    private List<User> readFromFile(String pathname) {
        try {
            File file = openDataFile(pathname);
            return objectMapper.readValue(file, new TypeReference<List<User>>() {});
        } catch (IOException e) {
            throw new RuntimeException("Open data file failed!");
        }
    }

    private void writeToFile(List<User> users, String pathname) {
        try {
            File file = openDataFile(pathname);
            saveToDataFile(file, objectMapper.writeValueAsString(users));
        } catch (IOException e) {
            throw new RuntimeException("Open data file failed!");
        }
    }

    private File openDataFile(String pathname) throws IOException {
        File file = new File(pathname);
        if (!file.exists()) {
            file.createNewFile();

            FileWriter fileWriter = new FileWriter(file);
            fileWriter.write("[]");
            fileWriter.close();
        }
        return file;
    }

    private void saveToDataFile(File file, String content) throws IOException {
        FileWriter fw = new FileWriter(file.getAbsoluteFile());
        BufferedWriter bw = new BufferedWriter(fw);
        bw.write(content);
        bw.close();
    }
}
