package teletubbies.item;

import java.util.Random;
import java.util.concurrent.atomic.AtomicReference;

import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ActionResult;
import net.minecraft.util.ActionResultType;
import net.minecraft.util.Hand;
import net.minecraft.util.math.MathHelper;
import net.minecraft.world.World;
import net.minecraftforge.common.util.LazyOptional;
import teletubbies.Teletubbies;
import teletubbies.client.audio.SoundList;
import teletubbies.common.capabilities.IJumpCapability;
import teletubbies.common.capabilities.JumpProvider;

public class LaaLaaBallItem extends Item {
		
	public LaaLaaBallItem() {
		super(new Item.Properties()
				.maxStackSize(1)
				.group(Teletubbies.ITEMGROUP));
		
		this.setRegistryName(Teletubbies.MODID, "laalaa_ball");
	}
	
	@Override
    public ActionResult<ItemStack> onItemRightClick(World world, PlayerEntity player, Hand hand) {	
		ItemStack stack = player.getHeldItem(hand);
		
		if(hand == Hand.OFF_HAND && player.getHeldItemMainhand() != null && player.getHeldItemMainhand().getItem() instanceof LaaLaaBallItem) {
	        return new ActionResult<ItemStack>(ActionResultType.FAIL, stack);
		}
				
		LazyOptional<IJumpCapability> cap = player.getCapability(JumpProvider.JUMP_CAPABILITY, player.getHorizontalFacing());
		AtomicReference<ActionResultType> ar = new AtomicReference<>();
		ar.set(ActionResultType.FAIL);
		
		if(!cap.isPresent()) {
	        return new ActionResult<ItemStack>(ActionResultType.FAIL, stack);
		}
		
		cap.ifPresent(c -> {
			if(c.canJump(player) && c.check()) {
				LaaLaaBallItem.jump(player, false);
				ar.set(ActionResultType.PASS);
			}
		});
		
        return new ActionResult<ItemStack>(ar.get(), stack);
    }
	
	public static void jump(PlayerEntity player, boolean fallJump) {
		LazyOptional<IJumpCapability> cap = player.getCapability(JumpProvider.JUMP_CAPABILITY, player.getHorizontalFacing());

		cap.ifPresent(c -> {
			float fallDistance = c.fallDistance();
			double motionY;
			
			if(fallJump) motionY = 1 + fallDistance / 100;
			else motionY = 1;
			
			float yaw = player.rotationYaw;
			float pitch = player.rotationPitch;
			float movingAmount = 1.0F;
			double motionX = (double)(-MathHelper.sin(yaw / 180.0F * (float)Math.PI) * MathHelper.cos(pitch / 180.0F * (float)Math.PI) * movingAmount);
			double motionZ = (double)(MathHelper.cos(yaw / 180.0F * (float)Math.PI) * MathHelper.cos(pitch / 180.0F * (float)Math.PI) * movingAmount);
			
			player.addVelocity(motionX, motionY, motionZ);
		    Random rand = new Random();
		    float pitch1 = (float) (rand.nextFloat() * (1.1 - 0.9) + 0.9);
			player.playSound(SoundList.BALL_BOUNCE, 1, pitch1);
		});
	}
}