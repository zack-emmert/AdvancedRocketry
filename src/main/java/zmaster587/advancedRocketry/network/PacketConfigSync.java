package zmaster587.advancedRocketry.network;

import io.netty.buffer.ByteBuf;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.network.PacketBuffer;
import zmaster587.advancedRocketry.api.ARConfiguration;
import zmaster587.advancedRocketry.api.dimension.solar.StellarBody;
import zmaster587.advancedRocketry.dimension.DimensionManager;
import zmaster587.libVulpes.network.BasePacket;

import java.io.IOException;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;

public class PacketConfigSync extends BasePacket {
	ARConfiguration config;
	int spaceDimId;
	int stationSize;
	boolean planetsMustBeDiscovered;
	LinkedList<Integer> knownPlanets;
	
	public PacketConfigSync(ARConfiguration config) {
		this.config = config;
	}
	
	
	public PacketConfigSync() {
		this.config = new ARConfiguration(ARConfiguration.getCurrentConfig());
	}
	
	@Override
	public void write(ByteBuf out) {
		config.writeConfigToNetwork( new PacketBuffer( out));
	}

	@Override
	public void readClient(ByteBuf in) {
		config = config.readConfigFromNetwork(new PacketBuffer(in));
	}

	@Override
	public void read(ByteBuf in) {
		//nice try
	}

	@Override
	public void executeClient(EntityPlayer thePlayer) {
		try {
			ARConfiguration.loadConfigFromServer(config);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Override
	public void executeServer(EntityPlayerMP player) {}

}
