package com.dubrouskaya.springsecurity.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CachedValue {
    int attempts;
    LocalDateTime blockedTimestamp;
}
