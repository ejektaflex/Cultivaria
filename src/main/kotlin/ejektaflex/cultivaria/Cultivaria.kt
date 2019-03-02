package ejektaflex.cultivaria

import com.google.gson.GsonBuilder
import ejektaflex.cultivaria.util.allNeighbors
import ejektaflex.cultivaria.util.toPretty
import net.alexwells.kottle.FMLKotlinModLoadingContext
import net.minecraft.block.Block
import net.minecraft.init.Blocks
import net.minecraftforge.common.MinecraftForge
import net.minecraftforge.event.RegistryEvent
import net.minecraftforge.eventbus.api.SubscribeEvent
import net.minecraftforge.fml.InterModComms
import net.minecraftforge.fml.common.Mod
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent
import net.minecraftforge.fml.event.lifecycle.InterModEnqueueEvent
import net.minecraftforge.fml.event.lifecycle.InterModProcessEvent
import net.minecraftforge.fml.event.server.FMLServerStartingEvent
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext
import net.alexwells.kottle.KotlinEventBusSubscriber
import net.minecraftforge.event.world.BlockEvent
import org.apache.logging.log4j.LogManager
import org.apache.logging.log4j.Logger
import java.io.File
import java.nio.file.Paths
import java.util.function.Consumer

import java.util.stream.Collectors

// The value here should match an entry in the META-INF/mods.toml file
@Mod("cultivaria")
object Cultivaria {

    val gson = GsonBuilder().setPrettyPrinting().create()

    val configFolder = Paths.get("config", "cultivaria").toFile().apply {
        mkdirs()
    }

    val mutationFile = File(configFolder, "mutations.json").apply {
        if (!exists()) {
            createNewFile()
            writeText(gson.toJson(MutationRegistry.defaultData.content))
        }
    }

    init {
        FMLKotlinModLoadingContext.get().modEventBus.addListener<FMLCommonSetupEvent> { setup(it) }
        MinecraftForge.EVENT_BUS.register(this)
    }

    private fun setup(event: FMLCommonSetupEvent) {
        MutationRegistry.restore(MutationRegistry.defaultData.content)
    }

    @SubscribeEvent
    fun onGrow(event: BlockEvent.CropGrowEvent) {
        MutationUtil.spreadMutationAttempt(event.world, event.state, event.pos)
    }

    private val LOGGER = LogManager.getLogger()

}
