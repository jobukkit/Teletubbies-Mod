package teletubbies.world.gen.feature.structure;

import java.util.Random;

import net.minecraft.core.BlockPos;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.ChunkPos;
import net.minecraft.world.level.ServerLevelAccessor;
import net.minecraft.world.level.StructureFeatureManager;
import net.minecraft.world.level.WorldGenLevel;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.RedstoneLampBlock;
import net.minecraft.world.level.block.RedstoneTorchBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraft.world.level.chunk.ChunkGenerator;
import net.minecraft.world.level.levelgen.structure.BoundingBox;
import net.minecraft.world.level.levelgen.structure.TemplateStructurePiece;
import net.minecraft.world.level.levelgen.structure.templatesystem.StructureManager;
import net.minecraft.world.level.levelgen.structure.templatesystem.StructurePlaceSettings;
import net.minecraft.world.level.levelgen.structure.templatesystem.StructureTemplate;
import teletubbies.Teletubbies;
import teletubbies.block.FullGrassBlock;
import teletubbies.init.TeletubbiesBlocks;
import teletubbies.init.TeletubbiesWorldGen;

public class DomePieces {
	
	public static final ResourceLocation DOME_TINKYWINKY = new ResourceLocation(Teletubbies.MODID, "dome_tinkywinky");
	public static final ResourceLocation DOME_DIPSY = new ResourceLocation(Teletubbies.MODID, "dome_dipsy");
	public static final ResourceLocation DOME_LAALAA = new ResourceLocation(Teletubbies.MODID, "dome_laalaa");
	public static final ResourceLocation DOME_PO = new ResourceLocation(Teletubbies.MODID, "dome_po");

	public static class Piece extends TemplateStructurePiece {
		
		private ResourceLocation resourceLocation;
		
	    public Piece(StructureManager manager, ResourceLocation rl, BlockPos pos) {
	        super(TeletubbiesWorldGen.DOME_PIECE, 0);
	        this.templatePosition = pos;
	        this.resourceLocation = rl;
	        this.setupPiece(manager);
	    }
	
	    public Piece(StructureManager manager, CompoundTag nbt) {
	        super(TeletubbiesWorldGen.DOME_PIECE, nbt);
	        this.resourceLocation = new ResourceLocation(nbt.getString("resourceLocation"));
	        this.setupPiece(manager);
	    }
	
	    private void setupPiece(StructureManager manager) {
	        StructureTemplate template = manager.getOrCreate(this.resourceLocation);
	        StructurePlaceSettings placementsettings = new StructurePlaceSettings();
	        this.setup(template, this.templatePosition, placementsettings);
	    }
	    
        @Override
        protected void addAdditionalSaveData(CompoundTag nbt) {
            super.addAdditionalSaveData(nbt);
            nbt.putString("resourceLocation", this.resourceLocation.toString());
        }
	    
	    @Override
	    public boolean postProcess(WorldGenLevel world, StructureFeatureManager manager, ChunkGenerator generator, Random rand, BoundingBox mbb, ChunkPos chunkPos, BlockPos pos) {
	    	boolean b = super.postProcess(world, manager, generator, rand, mbb, chunkPos, pos);
	    	
	    	if (b) {	    		
    			// Get all blocks in the sphere of the dome
	        	for (int i = 0; i < mbb.getXSpan(); i++) {
	        		for (int j = 0; j < mbb.getZSpan(); j++) {	        			
	        			if (this.getBlock(world, i, 0, j, mbb).getBlock() instanceof FullGrassBlock ||
	        				this.getBlock(world, i, 0, j, mbb).getBlock() instanceof RedstoneTorchBlock ||
	        				this.getBlock(world, i, 0, j, mbb).getBlock() instanceof RedstoneLampBlock) {
	            			
	        				this.fillColumnDown(world, TeletubbiesBlocks.FULL_GRASS.get().defaultBlockState(), i, -1, j, mbb);
	        				this.replaceAirAndLiquidDownwardsThrough(world, Blocks.AIR.defaultBlockState(), i, 8, j, 0, mbb);
	        			}
	        		}
	        	}
	    	}
	    	return b;
	    }
	
		@Override
		protected void handleDataMarker(String function, BlockPos pos, ServerLevelAccessor world, Random rand, BoundingBox mbb) {
			// Chest spawns but is not functional
			/*if (function.equals("chest")) {
				this.generateChest(world, mbb, rand, pos, TinkyWinkyBagItem.LOOTTABLE, Blocks.CHEST.getDefaultState().with(HorizontalBlock.HORIZONTAL_FACING, Direction.SOUTH));
			}*/
		}
		
		protected void replaceAirAndLiquidDownwardsThrough(WorldGenLevel world, BlockState blockstateIn, int x, int yHigh, int z, int yLow, BoundingBox mbb) {
			int i = this.getWorldX(x, z);
			int j = this.getWorldY(yHigh);
			int k = this.getWorldZ(x, z);
			final int low = this.getWorldY(yLow);
			if (mbb.isInside(new BlockPos(i, j, k))) {
				while (j >= low) {
					if ((world.isEmptyBlock(new BlockPos(i, j, k)) || world.getBlockState(new BlockPos(i, j, k)).getMaterial().isLiquid()) && j > 1) {
						world.setBlock(new BlockPos(i, j, k), blockstateIn, 2);
					}
					else if ((world.getBlockState(new BlockPos(i, j, k)).hasProperty(BlockStateProperties.WATERLOGGED)) && j > 1) {
						world.setBlock(new BlockPos(i, j, k), world.getBlockState(new BlockPos(i, j, k)).setValue(BlockStateProperties.WATERLOGGED, false), 2);
					}
					--j;
				}
			}
		}
	}
}