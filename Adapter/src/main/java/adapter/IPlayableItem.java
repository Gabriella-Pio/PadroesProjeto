/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package adapter;

/**
 *
 * @author gabriella
 */
public interface IPlayableItem {
    public String getTrackName();
    public String getArtistName();
    public void play();
    public String getSource();
}
