package cofh.thermaldynamics.duct.light;

import cofh.core.network.PacketHandler;
import cofh.thermaldynamics.duct.Duct;
import cofh.thermaldynamics.duct.DuctFactory;
import cofh.thermaldynamics.render.TextureOverlay;
import cofh.thermaldynamics.render.TextureTransparent;
import net.minecraft.client.renderer.texture.TextureMap;
import net.minecraftforge.fml.common.registry.GameRegistry;

public class DuctLight extends Duct {

	static {
		PacketHandler.instance.registerPacket(PacketLight.class);
		GameRegistry.registerTileEntity(TileLightDuct.class, "thermaldynamics.LightDuct");
	}

	public DuctLight(int id, int type, String name, Type ductType, DuctFactory factory, String baseTexture, String connectionTexture, String fluidTexture, int fluidTransparency) {

		super(id, false, 0, type, name, ductType, factory, baseTexture, connectionTexture, fluidTexture, fluidTransparency, null, null, 0);
	}

	@Override
	public void registerIcons(TextureMap textureMap) {

		iconBaseTexture = TextureOverlay.generateBaseTexture(textureMap, baseTexture);

		if (connectionTexture != null) {
			iconConnectionTexture = TextureOverlay.generateConnectionTexture(textureMap, connectionTexture);
		}
		if (fluidTexture != null) {
			iconFluidTexture = TextureTransparent.registerTransparentIcon(textureMap, fluidTexture, fluidTransparency);
		}
	}

}
