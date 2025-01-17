package teletubbies.inventory.container;

import net.minecraft.inventory.container.ContainerType;
import net.minecraftforge.common.extensions.IForgeContainerType;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import teletubbies.Teletubbies;

public class ContainerList {
	
	public static final DeferredRegister<ContainerType<?>> CONTAINER_TYPES = new DeferredRegister<>(ForgeRegistries.CONTAINERS, Teletubbies.MODID);
	
	public static final RegistryObject<ContainerType<TinkyWinkyBagContainer>> TINKYWINKY_BAG_CONTAINER = CONTAINER_TYPES
			.register("tinkywinky_bag_container", () -> IForgeContainerType.create(TinkyWinkyBagContainer::new));
	
	public static final RegistryObject<ContainerType<ControlPanelContainer>> CONTROL_PANEL_CONTAINER = CONTAINER_TYPES
			.register("control_panel_container", () -> IForgeContainerType.create(ControlPanelContainer::new));
	
	public static final RegistryObject<ContainerType<ToastMachineContainer>> TOAST_MACHINE_CONTAINER = CONTAINER_TYPES
			.register("toast_machine_container", () -> IForgeContainerType.create(ToastMachineContainer::new));	
	
	public static final RegistryObject<ContainerType<CustardMachineContainer>> CUSTARD_MACHINE_CONTAINER = CONTAINER_TYPES
			.register("custard_machine_container", () -> IForgeContainerType.create(CustardMachineContainer::new));
}
