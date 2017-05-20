package com.xx.server.client;

import com.xx.server.message.Packet;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Objects;
import java.util.concurrent.*;

/**
 * Created by gukt on 2016/7/30.
 *
 * @author gukt
 * @version 1.0
 */
public class WriteFuture implements Future<Packet> {
    private static final Logger LOGGER = LoggerFactory.getLogger(WriteFuture.class);

    private BlockingQueue<Packet> receivedMessages;
    private String identifier;

    public WriteFuture(BlockingQueue<Packet> packetQueue, String identifier) {
        this.receivedMessages = packetQueue;
        this.identifier = identifier;
    }

    @Override
    @SuppressWarnings("unchecked")
    public Packet get() throws InterruptedException, ExecutionException {
        for (; ; ) {
            Packet packet = receivedMessages.take();
            if (Objects.equals(packet.getCmd(), identifier)) {
                receivedMessages.clear();
                return packet;
            }
        }
    }

    @Override
    @SuppressWarnings("unchecked")
    public Packet get(long timeout, TimeUnit unit) throws InterruptedException, ExecutionException, TimeoutException {
        for (; ; ) {
            Packet packet = receivedMessages.poll(timeout, unit);
            if (Objects.equals(packet.getCmd(), identifier)) {
                receivedMessages.clear();
                return packet;
            }
        }
    }

    @Override
    public boolean cancel(boolean mayInterruptIfRunning) {
        return false;
    }

    @Override
    public boolean isCancelled() {
        return false;
    }

    @Override
    public boolean isDone() {
        return false;
    }
}
