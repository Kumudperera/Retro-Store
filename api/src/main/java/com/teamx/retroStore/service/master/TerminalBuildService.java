package com.teamx.retroStore.service.master;

import org.springframework.stereotype.Service;

import java.io.File;

@Service
public class TerminalBuildService {
    private static final String ENV_FILE_PATH = "path-to-tauri-app/.env";
    private static final String DESKTOP_APP_PATH = "/home/kumud/Projects/Personal/Retro-Store/desktop";
    private static final String BUILT_APP_PATH = "path-to-tauri-app/src-tauri/target/release/appname.exe";

    public boolean buildApp() throws Exception {
        ProcessBuilder processBuilder = new ProcessBuilder();
        processBuilder.directory(new File(DESKTOP_APP_PATH));
        processBuilder.command("bash", "-c", "npm install && npm run tauri build");

        Process process = processBuilder.start();
        int exitCode = process.waitFor();

        return exitCode == 0;
    }
}
