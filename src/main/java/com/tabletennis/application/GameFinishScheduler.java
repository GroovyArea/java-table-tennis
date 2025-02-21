package com.tabletennis.application;

import com.tabletennis.core.room.RoomReader;
import com.tabletennis.core.room.RoomWriter;
import jakarta.annotation.PreDestroy;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

@Component
@RequiredArgsConstructor
public class GameFinishScheduler {

    private static final int PARALLEL_THREAD_SIZE = 10;
    private static final int GAME_FINISH_MINUTES = 1;

    private final RoomReader roomReader;
    private final RoomWriter roomWriter;
    private final ScheduledExecutorService executorService = Executors.newScheduledThreadPool(PARALLEL_THREAD_SIZE);

    public void scheduleFinish(long roomId) {
        executorService.schedule(() -> {
            roomReader.findRoomBy(roomId)
                    .ifPresent(room -> {
                        room.finish();
                        roomWriter.saveRoom(room);
                    });
        }, GAME_FINISH_MINUTES, TimeUnit.MINUTES);
    }

    @PreDestroy
    public void shutdownScheduler() {
        executorService.shutdown();
    }
}