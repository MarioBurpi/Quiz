package cat.inspedralbes.projecte2damb.quizio.util;

import android.content.Context;
import android.media.AudioAttributes;
import android.media.SoundPool;
import android.os.Build;

import cat.inspedralbes.projecte2damb.quizio.R;

import static android.media.AudioManager.STREAM_MUSIC;

public class SoundManager {

    private SoundPool soundPool;
    private final int idSoundIntro, idSoundCorrect, idSoundWrong;
    private boolean soundOK;

    public SoundManager(Context context) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            SoundPool.Builder spBuilder = new SoundPool.Builder();
            AudioAttributes attributes = new AudioAttributes.Builder()
                    .setUsage(AudioAttributes.USAGE_MEDIA)
                    .setContentType(AudioAttributes.CONTENT_TYPE_MUSIC)
                    .build();
            soundPool = spBuilder.setMaxStreams(10).setAudioAttributes(attributes).build();
        } else {
            // OLD-Fashioned way
            soundPool = new SoundPool(10, STREAM_MUSIC, 0);
        }

        soundPool.setOnLoadCompleteListener(new SoundPool.OnLoadCompleteListener() {
            @Override
            public void onLoadComplete(SoundPool soundPool, int sampleId, int status) {
                if (status == 0) {
                    // Check which sound is loaded
                    if (sampleId == idSoundCorrect) {
                        soundOK = true;
                    }
                }
            }
        });
        // Finally, load the sound
        idSoundIntro = soundPool.load(context, R.raw.intro_splash, 1);
        idSoundCorrect = soundPool.load(context, R.raw.correct, 1);
        idSoundWrong = soundPool.load(context, R.raw.wrong, 1);
    }

    /**
     * Reproduces the sound
     */
    public void playSoundCorrect() {
        if (soundOK) {
            soundPool.play(idSoundCorrect, 0.5F, 0.5F, 0, 0, 1.0F);
            soundPool.stop(idSoundCorrect);
        }
    }

    public void playSoundWrong() {
        if (soundOK) {
            soundPool.play(idSoundWrong, 0.5F, 0.5F, 0, 0, 1.0F);
            soundPool.stop(idSoundWrong);
        }

    }

    public void playIntro() {
        if (soundOK) {
            soundPool.play(idSoundIntro, 0.5F, 0.5F, 0, 0, 1.0F);
            soundPool.stop(idSoundIntro);
        }
    }
}

