/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package entity;

/**
 *
 * @author gabriella
 */
public class LocalMusic {
    private String absolutePath;
    private String fileName;
    
    public LocalMusic(String absolutePath) {
        this.absolutePath = absolutePath;
    }
    
    public String getFileName() {
        return fileName;
    }

    public String getAbsolutePath() {
        return absolutePath;
    }
}
