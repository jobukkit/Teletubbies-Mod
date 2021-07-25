package teletubbies.block;

import javax.annotation.Nullable;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.MenuProvider;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.Mob;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Explosion;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraft.world.level.block.state.properties.BooleanProperty;
import net.minecraft.world.level.block.state.properties.DirectionProperty;
import net.minecraft.world.level.block.state.properties.EnumProperty;
import net.minecraft.world.level.material.FluidState;
import net.minecraft.world.level.material.Fluids;
import net.minecraft.world.level.material.Material;
import net.minecraft.world.level.pathfinder.BlockPathTypes;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.Shapes;
import net.minecraft.world.phys.shapes.VoxelShape;
import net.minecraftforge.common.ToolType;
import net.minecraftforge.fmllegacy.network.NetworkHooks;
import net.minecraftforge.items.CapabilityItemHandler;
import teletubbies.tileentity.CustardMachineSlaveTileEntity;
import teletubbies.tileentity.CustardMachineTileEntity;
import teletubbies.util.BlocksUtil;
import teletubbies.util.VoxelShapeRotation;

public class CustardMachineBlock extends Block {
	public static final DirectionProperty FACING = BlockStateProperties.HORIZONTAL_FACING;
	public static final EnumProperty<CustardMachinePart> PART = EnumProperty.create("part", CustardMachinePart.class);
	public static final BooleanProperty WATERLOGGED = BlockStateProperties.WATERLOGGED;
	public static final BooleanProperty LIT = BlockStateProperties.LIT;
		
	protected static final VoxelShape SMALLTOWER_AABB_NORTH = Shapes.or(
			box(7.0D, 0.0D, 5.0D, 13.0D, 3.0D, 11.0D), 
			box(8.0D, 3.0D, 6.0D, 12.0D, 10.0D, 10.0D), 
			box(7.0D, 10.0D, 5.0D, 13.0D, 11.0D, 11.0D), 
			box(8.0D, 11.0D, 6.0D, 12.0D, 13.0D, 10.0D),
			box(9.0D, 13.0D, 7.0D, 11.0D, 14.0D, 9.0D))
			.optimize();
	
	protected static final VoxelShape BIGTOWER_AABB_NORTH = Shapes.or(
			box(3.0D, 0.0D, 5.0D, 9.0D, 3.0D, 11.0D), 
			box(4.0D, 3.0D, 6.0D, 8.0D, 12.0D, 10.0D), 
			box(3.0D, 12.0D, 5.0D, 9.0D, 13.0D, 11.0D), 
			box(4.0D, 13.0D, 6.0D, 8.0D, 15.0D, 10.0D),
			box(5.0D, 15.0D, 7.0D, 7.0D, 16.0D, 9.0D),
			box(7.0D, 0.0D, 2.0D, 12.0D, 3.0D, 5.0D))
			.optimize();
	
	protected static final VoxelShape SMALLTOWER_AABB_EAST = VoxelShapeRotation.rotateY(SMALLTOWER_AABB_NORTH, Math.toRadians(270));
	protected static final VoxelShape SMALLTOWER_AABB_SOUTH = VoxelShapeRotation.rotateY(SMALLTOWER_AABB_NORTH, Math.toRadians(180));
	protected static final VoxelShape SMALLTOWER_AABB_WEST = VoxelShapeRotation.rotateY(SMALLTOWER_AABB_NORTH, Math.toRadians(90));
	protected static final VoxelShape BIGTOWER_AABB_EAST = VoxelShapeRotation.rotateY(BIGTOWER_AABB_NORTH, Math.toRadians(270));
	protected static final VoxelShape BIGTOWER_AABB_SOUTH = VoxelShapeRotation.rotateY(BIGTOWER_AABB_NORTH, Math.toRadians(180));
	protected static final VoxelShape BIGTOWER_AABB_WEST = VoxelShapeRotation.rotateY(BIGTOWER_AABB_NORTH, Math.toRadians(90));
		
	public CustardMachineBlock() {
		super(Properties.of(Material.METAL)
				.strength(3.0f, 5.0f)
				.harvestTool(ToolType.PICKAXE));

		this.registerDefaultState(this.stateDefinition.any().setValue(FACING, Direction.NORTH).setValue(PART, CustardMachinePart.BASE).setValue(WATERLOGGED, false).setValue(LIT, false));
	}
	
	@Override
    @Nullable
	public BlockPathTypes getAiPathNodeType(BlockState state, BlockGetter world, BlockPos pos, @Nullable Mob entity) {
        return BlockPathTypes.BLOCKED;
    }
	
	@Override
	public InteractionResult use(BlockState state, Level world, BlockPos pos, Player player, InteractionHand hand, BlockHitResult hit) {
		BlockPos tilePos = getBasePos(pos, state.getValue(PART), state.getValue(FACING));
		CustardMachineTileEntity te = (CustardMachineTileEntity) world.getBlockEntity(tilePos);

		if (!world.isClientSide && player instanceof ServerPlayer) {
			NetworkHooks.openGui((ServerPlayer) player, (MenuProvider) te, tilePos);
		}
		return InteractionResult.SUCCESS;
	}
	
	@Override
	public VoxelShape getShape(BlockState state, BlockGetter world, BlockPos pos, CollisionContext context) {
		if (state.getValue(PART) == CustardMachinePart.BASE || state.getValue(PART) == CustardMachinePart.BIGBASE || state.getValue(PART) == CustardMachinePart.SMALLBASE) {
			return Shapes.block();
		}
		if (state.getValue(PART) == CustardMachinePart.SMALL) {
			switch(state.getValue(FACING)) {
			case NORTH:
				return SMALLTOWER_AABB_NORTH;
			case EAST:
				return SMALLTOWER_AABB_EAST;
			case SOUTH:
				return SMALLTOWER_AABB_SOUTH;
			case WEST:
				return SMALLTOWER_AABB_WEST;
			default:
				return SMALLTOWER_AABB_NORTH;
			}
		}
		switch(state.getValue(FACING)) {
		case NORTH:
			return BIGTOWER_AABB_NORTH;
		case EAST:
			return BIGTOWER_AABB_EAST;
		case SOUTH:
			return BIGTOWER_AABB_SOUTH;
		case WEST:
			return BIGTOWER_AABB_WEST;
		default:
			return BIGTOWER_AABB_NORTH;
		}
	}
	
	@Override
	public void setPlacedBy(Level world, BlockPos pos, BlockState state, @Nullable LivingEntity placer, ItemStack stack) {
		if (placer != null) {
			BlockPos smallbasePos = getSmallTowerBasePos(pos, placer.getDirection());
			BlockPos bigbasePos = getBigTowerBasePos(pos, placer.getDirection());
			BlockPos smallPos = getSmallTowerPos(pos, placer.getDirection());
			BlockPos bigPos = getBigTowerPos(pos, placer.getDirection());
		    FluidState smallFluid = world.getFluidState(smallPos);
		    FluidState bigFluid = world.getFluidState(bigPos);
			world.setBlockAndUpdate(smallbasePos, state.setValue(PART, CustardMachinePart.SMALLBASE).setValue(WATERLOGGED, false));
		    world.setBlockAndUpdate(bigbasePos, state.setValue(PART, CustardMachinePart.BIGBASE).setValue(WATERLOGGED, false));
		    world.setBlockAndUpdate(smallPos, state.setValue(PART, CustardMachinePart.SMALL).setValue(WATERLOGGED, smallFluid.getType() == Fluids.WATER));
		    world.setBlockAndUpdate(bigPos, state.setValue(PART, CustardMachinePart.BIG).setValue(WATERLOGGED, bigFluid.getType() == Fluids.WATER));
		}
	}
	
	@Override
	public void playerWillDestroy(Level world, BlockPos pos, BlockState state, Player player) {
		Direction facing = state.getValue(FACING);
		
		BlockPos basePos = getBasePos(pos, state.getValue(PART), facing);
		BlockState subblockState = world.getBlockState(basePos);
		if (subblockState.getBlock() == this && !pos.equals(basePos)) {
			removePart(world, basePos, subblockState);
		}
		
		BlockPos subblock = getSmallTowerBasePos(basePos, facing);
		subblockState = world.getBlockState(subblock);
		if (subblockState.getBlock() == this && !pos.equals(subblock)) {	
			removePart(world, subblock, subblockState);
		}
		
		subblock = getBigTowerBasePos(basePos, facing);
		subblockState = world.getBlockState(subblock);
		if (subblockState.getBlock() == this && !pos.equals(subblock)) {
			removePart(world, subblock, subblockState);
		}
		
		subblock = getSmallTowerPos(basePos, facing);
		subblockState = world.getBlockState(subblock);
		if (subblockState.getBlock() == this && !pos.equals(subblock)) {	
			removePart(world, subblock, subblockState);
		}
		
		subblock = getBigTowerPos(basePos, facing);
		subblockState = world.getBlockState(subblock);
		if (subblockState.getBlock() == this && !pos.equals(subblock)) {		
			removePart(world, subblock, subblockState);
		}		      
		super.playerWillDestroy(world, pos, state, player);
	}
	
	@Override
    public void onBlockExploded(BlockState state, Level world, BlockPos pos, Explosion explosion) {
		Direction facing = state.getValue(FACING);
		
		BlockPos basePos = getBasePos(pos, state.getValue(PART), facing);
		BlockState subblockState = world.getBlockState(basePos);
		if (subblockState.getBlock() == this && !pos.equals(basePos)) {		      
			removePart(world, basePos, subblockState);
		}
		
		BlockPos subblock = getSmallTowerBasePos(basePos, facing);
		subblockState = world.getBlockState(subblock);
		if (subblockState.getBlock() == this && !pos.equals(subblock)) {		      
			removePart(world, subblock, subblockState);
		}
		
		subblock = getBigTowerBasePos(basePos, facing);
		subblockState = world.getBlockState(subblock);
		if (subblockState.getBlock() == this && !pos.equals(subblock)) {		      
			removePart(world, subblock, subblockState);
		}
		
		subblock = getSmallTowerPos(basePos, facing);
		subblockState = world.getBlockState(subblock);
		if (subblockState.getBlock() == this && !pos.equals(subblock)) {		      
			removePart(world, subblock, subblockState);
		}
		
		subblock = getBigTowerPos(basePos, facing);
		subblockState = world.getBlockState(subblock);
		if (subblockState.getBlock() == this && !pos.equals(subblock)) {		      
			removePart(world, subblock, subblockState);
		}
		super.onBlockExploded(state, world, pos, explosion);
    }
	
	private void removePart(Level world, BlockPos pos, BlockState state) {
		FluidState fluidState = world.getFluidState(pos);
	    if (fluidState.getType() == Fluids.WATER) {
			world.setBlock(pos, fluidState.createLegacyBlock(), 35); 
	    }
	    else {
	    	world.setBlock(pos, Blocks.AIR.defaultBlockState(), 35);
	    }
	}
	
	@Nullable
	@Override
	public BlockState getStateForPlacement(BlockPlaceContext context) {
		BlockPos pos = context.getClickedPos();
		BlockPos smallbasePos = getSmallTowerBasePos(pos, context.getHorizontalDirection());
		BlockPos bigbasePos = getBigTowerBasePos(pos, context.getHorizontalDirection());
		BlockPos smallPos = getSmallTowerPos(pos, context.getHorizontalDirection());
		BlockPos bigPos = getBigTowerPos(pos, context.getHorizontalDirection());
		if (pos.getY() < 255 &&
				smallbasePos.getY() < 255 && context.getLevel().getBlockState(smallbasePos).canBeReplaced(context) &&
				bigbasePos.getY() < 255 && context.getLevel().getBlockState(bigbasePos).canBeReplaced(context) &&
				smallPos.getY() < 255 && context.getLevel().getBlockState(smallPos).canBeReplaced(context) &&
				bigPos.getY() < 255 && context.getLevel().getBlockState(bigPos).canBeReplaced(context)) {
			
			return this.defaultBlockState().setValue(FACING, context.getHorizontalDirection()).setValue(PART, CustardMachinePart.BASE).setValue(WATERLOGGED, false);
		}
		return null;
	}
	
	@Override
	public FluidState getFluidState(BlockState state) {
		return state.getValue(WATERLOGGED) ? Fluids.WATER.getSource(false) : super.getFluidState(state);
	}
	
	@Override
	public BlockState updateShape(BlockState state, Direction facing, BlockState facingState, LevelAccessor world, BlockPos currentPos, BlockPos facingPos) {
		if (state.getValue(WATERLOGGED)) {
			world.getLiquidTicks().scheduleTick(currentPos, Fluids.WATER, Fluids.WATER.getTickDelay(world));
		}
		return super.updateShape(state, facing, facingState, world, currentPos, facingPos);
	}
	
	@Override
	protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> builder) {
		builder.add(FACING, PART, WATERLOGGED, LIT);
	}
	
	public static boolean isUnderwater(Level world, BlockPos pos) {
		Direction facing = world.getBlockState(pos).getValue(FACING);
		BlockPos basePos = getBasePos(pos, world.getBlockState(pos).getValue(PART), facing);
		if (BlocksUtil.isBlockSurrounded(world, basePos) &&
				BlocksUtil.isBlockSurrounded(world, getSmallTowerBasePos(basePos, facing)) &&
				BlocksUtil.isBlockSurrounded(world, getBigTowerBasePos(basePos, facing)) &&
				world.getBlockState(getSmallTowerPos(basePos, facing)).getValue(WATERLOGGED) &&
				world.getBlockState(getBigTowerPos(basePos, facing)).getValue(WATERLOGGED)) { 
			return true; 
		}
		return false;
	}
	
	@Override
	public boolean hasTileEntity(BlockState state) {
		return (state.getValue(PART) == CustardMachinePart.BASE || state.getValue(PART) == CustardMachinePart.BIGBASE || state.getValue(PART) == CustardMachinePart.SMALLBASE);
	}
	
	@Nullable
	@Override
	public BlockEntity createTileEntity(BlockState state, BlockGetter world) {
		if (state.getValue(PART) == CustardMachinePart.BASE) {
			return new CustardMachineTileEntity();
		}
		else if (state.getValue(PART) == CustardMachinePart.BIGBASE) {
			return new CustardMachineSlaveTileEntity();
		}
		else if (state.getValue(PART) == CustardMachinePart.SMALLBASE) {
			return new CustardMachineSlaveTileEntity();
		}
		return null;
	}
	
	@Override
	public void onRemove(BlockState state, Level world, BlockPos pos, BlockState newState, boolean isMoving) {
		if (state.hasTileEntity() && state.getBlock() != newState.getBlock() && state.getValue(PART) == CustardMachinePart.BASE) {
			world.getBlockEntity(pos).getCapability(CapabilityItemHandler.ITEM_HANDLER_CAPABILITY).ifPresent(h -> {
				for (int i = 0; i < h.getSlots(); i++) {
					popResource(world, pos, h.getStackInSlot(i));
				}
			});
			world.getBlockEntity(pos).getCapability(CapabilityItemHandler.ITEM_HANDLER_CAPABILITY, Direction.DOWN).ifPresent(h -> {
				for (int i = 0; i < h.getSlots(); i++) {
					popResource(world, pos, h.getStackInSlot(i));
				}
			});
			world.removeBlockEntity(pos);
		}
	}
	
	@Override
	public int getLightValue(BlockState state, BlockGetter world, BlockPos pos) {
		return state.getValue(LIT) ? 6 : 0;
	}
	
	public static BlockPos getSmallTowerBasePos(BlockPos base, Direction facing) {
		switch (facing) {
		case NORTH:
			return base.east();
		case EAST:
			return base.south();
		case SOUTH:
			return base.west();
		case WEST:
			return base.north();
		default:
			return base.east();		
		}	
	}
	
	public static BlockPos getBigTowerBasePos(BlockPos base, Direction facing) {
		switch (facing) {
		case NORTH:
			return base.west();
		case EAST:
			return base.north();
		case SOUTH:
			return base.east();
		case WEST:
			return base.south();
		default:
			return base.west();		
		}	
	}
	
	public static BlockPos getSmallTowerPos(BlockPos base, Direction facing) {
		switch (facing) {
		case NORTH:
			return base.east().above();
		case EAST:
			return base.south().above();
		case SOUTH:
			return base.west().above();
		case WEST:
			return base.north().above();
		default:
			return base.east().above();		
		}	
	}
	
	public static BlockPos getBigTowerPos(BlockPos base, Direction facing) {
		switch (facing) {
		case NORTH:
			return base.west().above();
		case EAST:
			return base.north().above();
		case SOUTH:
			return base.east().above();
		case WEST:
			return base.south().above();
		default:
			return base.west().above();		
		}	
	}
	
	public static BlockPos getBasePos(BlockPos pos, CustardMachinePart part, Direction facing) {
		if (part == CustardMachinePart.BASE) return pos;
		switch (facing) {
		case NORTH:
			switch (part) {
			case BIG:
				return pos.east().below();
			case BIGBASE:
				return pos.east();
			case SMALL:
				return pos.west().below();
			case SMALLBASE:
				return pos.west();
			default:
				return null;
			}
		case EAST:
			switch (part) {
			case BIG:
				return pos.south().below();
			case BIGBASE:
				return pos.south();
			case SMALL:
				return pos.north().below();
			case SMALLBASE:
				return pos.north();
			default:
				return null;
			}
		case SOUTH:
			switch (part) {
			case BIG:
				return pos.west().below();
			case BIGBASE:
				return pos.west();
			case SMALL:
				return pos.east().below();
			case SMALLBASE:
				return pos.east();
			default:
				return null;
			}
		case WEST:
			switch (part) {
			case BIG:
				return pos.north().below();
			case BIGBASE:
				return pos.north();
			case SMALL:
				return pos.south().below();
			case SMALLBASE:
				return pos.south();
			default:
				return null;
			}
		default:
			return null;
		}
	}
}
