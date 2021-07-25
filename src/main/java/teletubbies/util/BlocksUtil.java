package teletubbies.util;

import net.minecraft.core.BlockPos;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Blocks;

public class BlocksUtil {
	public static boolean isBlockSurrounded(Level world, BlockPos pos) {
		if (world.getBlockState(pos.above()).getBlock() == Blocks.AIR) {
			return false;
		}
		if (world.getBlockState(pos.below()).getBlock() == Blocks.AIR) {
			return false;
		}
		if (world.getBlockState(pos.north()).getBlock() == Blocks.AIR) {
			return false;
		}
		if (world.getBlockState(pos.east()).getBlock() == Blocks.AIR) {
			return false;
		}
		if (world.getBlockState(pos.south()).getBlock() == Blocks.AIR) {
			return false;
		}
		if (world.getBlockState(pos.west()).getBlock() == Blocks.AIR) {
			return false;
		}
		return true;
	}
}
