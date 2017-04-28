package Sound;

import java.applet.Applet;
import java.applet.AudioClip;

/**
 * Created by Melany on 17/04/2017.
 */
public class Sounds {
    public static final AudioClip EXPLOSION = Applet.newAudioClip(Sounds.class.getResource("/Sounds/Explosion.wav"));
    public static final AudioClip BULLET = Applet.newAudioClip(Sounds.class.getResource("/Sounds/Bullet.wav"));
    public static final AudioClip LASER = Applet.newAudioClip(Sounds.class.getResource("/Sounds/Laser.wav"));
    public static final AudioClip MISSILE = Applet.newAudioClip(Sounds.class.getResource("/Sounds/Missile.wav"));
    public static final AudioClip SHIELD = Applet.newAudioClip(Sounds.class.getResource("/Sounds/Shield.wav"));
    public static final AudioClip GAMEOVER = Applet.newAudioClip(Sounds.class.getResource("/Sounds/Fail.wav"));
    public static final AudioClip BACKGROUND =Applet.newAudioClip(Sounds.class.getResource("/Sounds/MetalSlug.wav"));
    public static final AudioClip VICTORY = Applet.newAudioClip(Sounds.class.getResource("/Sounds/Victory.wav"));


}
