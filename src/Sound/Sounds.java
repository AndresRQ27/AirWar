package Sound;

import java.applet.Applet;
import java.applet.AudioClip;

/**
 * Created by Melany on 17/04/2017.
 */
public class Sounds {
    public static AudioClip EXPLOSION = Applet.newAudioClip(Sounds.class.getResource("/Explosion.wav"));
    public static AudioClip BULLET = Applet.newAudioClip(Sounds.class.getResource("/Bullet.wav"));
    public static AudioClip LASER = Applet.newAudioClip(Sounds.class.getResource("/Laser.wav"));
    public static AudioClip MISSILE = Applet.newAudioClip(Sounds.class.getResource("/Missile.wav"));
    public static AudioClip SHIELD = Applet.newAudioClip(Sounds.class.getResource("/Shield.wav"));
    //public static final AudioClip GAMEOVER = Applet.newAudioClip(Sounds.class.getResource("/GameOver.wav"));


}