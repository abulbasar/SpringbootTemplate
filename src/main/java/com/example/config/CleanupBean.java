package com.example.config;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.DisposableBean;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class CleanupBean implements DisposableBean {
    @Override
    public void destroy() throws Exception {
        log.info("Cleanup");
    }
}
