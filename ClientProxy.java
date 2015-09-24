package tutorial.basic;
 
import cpw.mods.fml.client.registry.RenderingRegistry;
import net.minecraftforge.client.MinecraftForgeClient;
 
public class ClientProxy extends CommonProxy
{
       
        public void registerRenderers()
        {
        	RenderingRegistry.registerEntityRenderingHandler(Changeling.class, new RenderChangeling(new ModelChangeling(), 0.5F));
        	RenderingRegistry.registerEntityRenderingHandler(Breezy.class, new RenderBreezy(new ModelBreezy(), 0.5F));
        	RenderingRegistry.registerEntityRenderingHandler(Tirek.class, new RenderTirek(new ModelTirek(), 0.5F));
        }
       
}