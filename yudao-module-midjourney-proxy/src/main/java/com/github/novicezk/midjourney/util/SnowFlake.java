package com.github.novicezk.midjourney.util;

import cn.hutool.core.exceptions.ValidateException;
import com.github.novicezk.midjourney.exception.SnowFlakeException;
import lombok.extern.slf4j.Slf4j;

import java.lang.management.ManagementFactory;
import java.net.InetAddress;
import java.net.NetworkInterface;
import java.util.Date;
import java.util.concurrent.ThreadLocalRandom;

@Slf4j
public class SnowFlake {
	private long workerId;
	private long datacenterId;
	private long sequence = 0L;
	private final long twepoch;
	private final long sequenceMask;
	private final long workerIdShift;
	private final long datacenterIdShift;
	private final long timestampLeftShift;
	private long lastTimestamp = -1L;
	private final boolean randomSequence;
	private long count = 0L;
	private final long timeOffset;
	private final ThreadLocalRandom tlr = ThreadLocalRandom.current();

	public static final SnowFlake INSTANCE = new SnowFlake();

	private SnowFlake() {
		this(false, 10, null, 5L, 5L, 12L);
	}

	private SnowFlake(boolean randomSequence, long timeOffset, Date epochDate, long workerIdBits, long datacenterIdBits, long sequenceBits) {
		if (null != epochDate) {
			this.twepoch = epochDate.getTime();
		} else {
			// 2012/12/12 23:59:59 GMT
			this.twepoch = 1355327999000L;
		}
		long maxWorkerId = ~(-1L << workerIdBits);
		long maxDatacenterId = ~(-1L << datacenterIdBits);
		this.sequenceMask = ~(-1L << sequenceBits);
		this.workerIdShift = sequenceBits;
		this.datacenterIdShift = sequenceBits + workerIdBits;
		this.timestampLeftShift = sequenceBits + workerIdBits + datacenterIdBits;
		this.randomSequence = randomSequence;
		this.timeOffset = timeOffset;
		try {
			this.datacenterId = getDatacenterId(maxDatacenterId);
			this.workerId = getMaxWorkerId(datacenterId, maxWorkerId);
		} catch (Exception e) {
			log.warn("datacenterId or workerId generate error: {}, set default value", e.getMessage());
			this.datacenterId = 4;
			this.workerId = 1;
		}
	}

	public synchronized String nextId() {
		long currentTimestamp = timeGen();
		if (currentTimestamp < this.lastTimestamp) {
			long offset = this.lastTimestamp - currentTimestamp;
			if (offset > this.timeOffset) {
				throw new ValidateException("Clock moved backwards, refusing to generate id for [" + offset + "ms]");
			}
			try {
				this.wait(offset << 1);
			} catch (InterruptedException e) {
				throw new SnowFlakeException(e);
			}
			currentTimestamp = timeGen();
			if (currentTimestamp < this.lastTimestamp) {
				throw new SnowFlakeException("Clock moved backwards, refusing to generate id for [" + offset + "ms]");
			}
		}
		if (this.lastTimestamp == currentTimestamp) {
			long tempSequence = this.sequence + 1;
			if (this.randomSequence) {
				this.sequence = tempSequence & this.sequenceMask;
				this.count = (this.count + 1) & this.sequenceMask;
				if (this.count == 0) {
					currentTimestamp = this.tillNextMillis(this.lastTimestamp);
				}
			} else {
				this.sequence = tempSequence & this.sequenceMask;
				if (this.sequence == 0) {
					currentTimestamp = this.tillNextMillis(lastTimestamp);
				}
			}
		} else {
			this.sequence = this.randomSequence ? this.tlr.nextLong(this.sequenceMask + 1) : 0L;
			this.count = 0L;
		}
		this.lastTimestamp = currentTimestamp;
		long id = ((currentTimestamp - this.twepoch) << this.timestampLeftShift) |
				(this.datacenterId << this.datacenterIdShift) |
				(this.workerId << this.workerIdShift) |
				this.sequence;
		return String.valueOf(id);
	}

	private static long getDatacenterId(long maxDatacenterId) {
		long id = 0L;
		try {
			InetAddress ip = InetAddress.getLocalHost();
			NetworkInterface network = NetworkInterface.getByInetAddress(ip);
			if (network == null) {
				id = 1L;
			} else {
				byte[] mac = network.getHardwareAddress();
				if (null != mac) {
					id = ((0x000000FF & (long) mac[mac.length - 1]) | (0x0000FF00 & (((long) mac[mac.length - 2]) << 8))) >> 6;
					id = id % (maxDatacenterId + 1);
				}
			}
		} catch (Exception e) {
			throw new SnowFlakeException(e);
		}
		return id;
	}

	private static long getMaxWorkerId(long datacenterId, long maxWorkerId) {
		StringBuilder macIpPid = new StringBuilder();
		macIpPid.append(datacenterId);
		try {
			String name = ManagementFactory.getRuntimeMXBean().getName();
			if (name != null && !name.isEmpty()) {
				macIpPid.append(name.split("@")[0]);
			}
			String hostIp = InetAddress.getLocalHost().getHostAddress();
			String ipStr = hostIp.replace("\\.", "");
			macIpPid.append(ipStr);
		} catch (Exception e) {
			throw new SnowFlakeException(e);
		}
		return (macIpPid.toString().hashCode() & 0xffff) % (maxWorkerId + 1);
	}

	private long tillNextMillis(long lastTimestamp) {
		long timestamp = timeGen();
		while (timestamp <= lastTimestamp) {
			timestamp = timeGen();
		}
		return timestamp;
	}

	private long timeGen() {
		return System.currentTimeMillis();
	}

}
