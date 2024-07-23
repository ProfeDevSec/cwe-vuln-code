package com.example.bank.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.lang.management.ManagementFactory;
import java.lang.management.OperatingSystemMXBean;

@RestController
@RequestMapping("/system-info")
public class SystemInfoController {

    // CWE-497: Exposure of Sensitive System Information to an Unauthorized Control Sphere
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
