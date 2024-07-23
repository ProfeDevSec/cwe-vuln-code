package com.example.bank.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.lang.management.ManagementFactory;
import java.lang.management.OperatingSystemMXBean;

@RestController
@RequestMapping("/system-info")
public class SystemInfoController {

    @Value("${system.info.allowedRole}")
    private String allowedRole;

    // Corrected to restrict access to authorized users only
    @PreAuthorize("hasRole(#allowedRole)")
    @GetMapping("/details")
    public String getSystemInfo() {
        OperatingSystemMXBean osBean = ManagementFactory.getOperatingSystemMXBean();
        String systemInfo = "OS Name: " + osBean.getName() + "\n" +
                            "OS Version: " + osBean.getVersion() + "\n" +
                            "Available Processors: " + osBean.getAvailableProcessors() + "\n" +
                            "System Load Average: " + osBean.getSystemLoadAverage();
        return systemInfo;
    }
}
