package common.source;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

public class NetworkEmulation implements INetworkEmulation {
	public enum NetworkDelay {
		GSM ("gsm"),
		HSCSD ("hscsd"),
		GPRS ("gprs"),
		EDGE ("edge"),
		UMTS ("umts"),
		HSDPA ("hsdpa"),
		LTE ("lte"),
		EVDO ("evdo"),
		NONE ("none"),
		NUM ("num"),
		MINMAX ("min:max");

		private final String name;

		NetworkDelay(String nm) {
			name = nm;
		}

		public String toString() {
			return this.name;
		}
	}

	public enum NetworkSpeed {
		GSM ("gsm"),
		HSCSD ("hscsd"),
		GPRS ("gprs"),
		EDGE ("edge"),
		UMTS ("umts"),
		HSDPA ("hsdpa"),
		LTE ("lte"),
		EVDO ("evdo"),
		FULL ("full"),
		NUM ("num"),
		UPDOWN ("up:down");

		private final String name;

		NetworkSpeed(String nm) {
			name = nm;
		}

		public String toString() {
			return this.name;
		}
	}

	private NetworkDelay networkDelay;
	private NetworkSpeed networkSpeed;

	private Integer speedUp = 0;
	private Integer speedDown = 0;
	private Integer delayMin = 0;
	private Integer delayMax = 0;

	private Integer delay = 0;
	private Integer speed = 0;

	private Boolean netfast = false;

	private String tcpDumpFilePath;

	public static NetworkEmulation createDefault() {
		return new NetworkEmulation()
				.setNetfast(true);
	}

	public static NetworkEmulation createNew(NetworkDelay delay, NetworkSpeed speed, String tcpDumpFile) {
		return new NetworkEmulation()
				.setNetworkDelay(delay)
				.setNetworkSpeed(speed)
				.setTcpDumpFilePath(tcpDumpFile);
	}

	public static NetworkEmulation createNew(NetworkSpeed speed, Integer networkDelay, String tcpDumpFile) {
		return new NetworkEmulation()
				.setNetworkDelay(NetworkDelay.NUM)
				.setNetworkSpeed(speed)
				.setDelay(networkDelay)
				.setTcpDumpFilePath(tcpDumpFile);
	}

	public static NetworkEmulation createNew(NetworkSpeed speed, Integer minNetworkDelay, Integer maxNetworkDelay, String tcpDumpFile) {
		return new NetworkEmulation()
				.setNetworkDelay(NetworkDelay.MINMAX)
				.setNetworkSpeed(speed)
				.setDelayMin(minNetworkDelay)
				.setDelayMax(maxNetworkDelay)
				.setTcpDumpFilePath(tcpDumpFile);
	}

	public static NetworkEmulation createNew(NetworkDelay delay, Integer networkSpeed, String tcpDumpFile) {
		return new NetworkEmulation()
				.setNetworkDelay(delay)
				.setNetworkSpeed(NetworkSpeed.NUM)
				.setSpeed(networkSpeed)
				.setTcpDumpFilePath(tcpDumpFile);
	}

	public static NetworkEmulation createNew(NetworkDelay delay, Integer upNetworkSpeed, Integer downNetworkSpeed, String tcpDumpFile) {
		return new NetworkEmulation()
				.setNetworkDelay(delay)
				.setNetworkSpeed(NetworkSpeed.UPDOWN)
				.setSpeedUp(upNetworkSpeed)
				.setSpeedDown(downNetworkSpeed)
				.setTcpDumpFilePath(tcpDumpFile);
	}

	public static NetworkEmulation createNew(Integer minNetworkDelay, Integer maxNetworkDelay, Integer upNetworkSpeed, Integer downNetworkSpeed, String tcpDumpFile) {
		return new NetworkEmulation()
				.setNetworkDelay(NetworkDelay.MINMAX)
				.setNetworkSpeed(NetworkSpeed.UPDOWN)
				.setDelayMin(minNetworkDelay)
				.setDelayMax(maxNetworkDelay)
				.setSpeedUp(upNetworkSpeed)
				.setSpeedDown(downNetworkSpeed)
				.setTcpDumpFilePath(tcpDumpFile);
	}

	public static NetworkEmulation createNew(Integer delay, Integer speed, String tcpDumpFile) {
		return new NetworkEmulation()
				.setNetworkDelay(NetworkDelay.NUM)
				.setNetworkSpeed(NetworkSpeed.NUM)
				.setDelay(delay)
				.setSpeed(speed)
				.setTcpDumpFilePath(tcpDumpFile);
	}

	public NetworkDelay getNetworkDelay() {
		return networkDelay;
	}

	public NetworkEmulation setNetworkDelay(NetworkDelay networkDelay) {
		this.networkDelay = networkDelay;
		return this;
	}

	public NetworkSpeed getNetworkSpeed() {
		return networkSpeed;
	}

	public NetworkEmulation setNetworkSpeed(NetworkSpeed networkSpeed) {
		this.networkSpeed = networkSpeed;
		return this;
	}

	public Integer getSpeedUp() {
		return speedUp;
	}

	public NetworkEmulation setSpeedUp(Integer speedUp) {
		this.speedUp = speedUp;
		return this;
	}

	public Integer getSpeedDown() {
		return speedDown;
	}

	public NetworkEmulation setSpeedDown(Integer speedDown) {
		this.speedDown = speedDown;
		return this;
	}

	public Integer getDelayMin() {
		return delayMin;
	}

	public NetworkEmulation setDelayMin(Integer delayMin) {
		this.delayMin = delayMin;
		return this;
	}

	public Integer getDelayMax() {
		return delayMax;
	}

	public NetworkEmulation setDelayMax(Integer delayMax) {
		this.delayMax = delayMax;
		return this;
	}

	public Boolean getNetfast() {
		return netfast;
	}

	public NetworkEmulation setNetfast(Boolean netfast) {
		this.netfast = netfast;
		return this;
	}

	public Integer getDelay() {
		return delay;
	}

	public NetworkEmulation setDelay(Integer delay) {
		this.delay = delay;
		return this;
	}

	public Integer getSpeed() {
		return speed;
	}

	public NetworkEmulation setSpeed(Integer speed) {
		this.speed = speed;
		return this;
	}

	public String getTcpDumpFilePath() {
		return tcpDumpFilePath;
	}

	public NetworkEmulation setTcpDumpFilePath(String tcpDumpFilePath) {
		this.tcpDumpFilePath = tcpDumpFilePath;
		return this;
	}

	public String toEmulatorArgs() {
		if (getNetfast()) {
			return "-netfast";
		}

		StringBuilder args = new StringBuilder();

		if (getNetworkDelay() != null) {
			args.append("-netdelay ");
			if (getNetworkDelay().equals(NetworkDelay.MINMAX)) {
				args.append(String.format("%d:%d ", getDelayMin(), getDelayMax()));
			} else if (getNetworkDelay().equals(NetworkDelay.NUM)) {
				args.append(String.format("%d ", getDelay()));
			} else {
				args.append(String.format("%s ", getNetworkDelay()));
			}
		}

		if (getNetworkSpeed() != null) {
			args.append("-netspeed ");
			if (getNetworkSpeed().equals(NetworkSpeed.UPDOWN)) {
				args.append(String.format("%d:%d ", getSpeedUp(), getSpeedDown()));
			} else if (getNetworkSpeed().equals(NetworkSpeed.NUM)) {
				args.append(String.format("%d ", getSpeed()));
			} else {
				args.append(String.format("%s ", getNetworkSpeed()));
			}
		}

		if (!BaseHelper.isNullOrEmpty(getTcpDumpFilePath())) {
			args.append(String.format("-engine classic -tcpdump %s", getTcpDumpFilePath()));
		}

		return args.toString();
	}

	@Override
	public String toString() {
		return ToStringBuilder.reflectionToString(this, ToStringStyle.DEFAULT_STYLE);
	}
}