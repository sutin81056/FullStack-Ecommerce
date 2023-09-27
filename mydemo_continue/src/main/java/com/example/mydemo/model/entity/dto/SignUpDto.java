package com.example.mydemo.model.entity.dto;

// record確保資料建立後不可變更 (Java 14)
public record SignUpDto (String username, char[] password) {

}
