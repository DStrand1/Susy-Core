package supersymmetry.common.metatileentities.multi.electric;

import gregtech.api.metatileentity.MetaTileEntity;
import gregtech.api.metatileentity.interfaces.IGregTechTileEntity;
import gregtech.api.metatileentity.multiblock.IMultiblockPart;
import gregtech.api.metatileentity.multiblock.RecipeMapMultiblockController;
import gregtech.api.pattern.BlockPattern;
import gregtech.api.pattern.FactoryBlockPattern;
import gregtech.api.unification.material.Materials;
import gregtech.client.renderer.ICubeRenderer;
import gregtech.client.renderer.texture.Textures;
import gregtech.common.blocks.BlockBoilerCasing.BoilerCasingType;
import gregtech.common.blocks.BlockMetalCasing.MetalCasingType;
import gregtech.common.blocks.BlockTurbineCasing;
import gregtech.common.blocks.MetaBlocks;
import gregtech.common.blocks.StoneVariantBlock;
import net.minecraft.block.state.IBlockState;
import net.minecraft.util.ResourceLocation;
import supersymmetry.api.recipes.SuSyRecipeMaps;

import javax.annotation.Nonnull;

public class MetaTileEntityRailroadEngineeringStation extends RecipeMapMultiblockController {

    public MetaTileEntityRailroadEngineeringStation(ResourceLocation metaTileEntityId) {
        super(metaTileEntityId, SuSyRecipeMaps.RAILROAD_ENGINEERING_STATION_RECIPES);
    }

    @Override
    public MetaTileEntity createMetaTileEntity(IGregTechTileEntity iGregTechTileEntity) {
        return new MetaTileEntityRailroadEngineeringStation(metaTileEntityId);
    }

    @Override
    protected BlockPattern createStructurePattern() {
        return FactoryBlockPattern.start()
                .aisle("BB     BB", "A       A", "A       A", "A       A", "A       A", "A       A", "         ", "         ")
                .aisle("BB     BB", "         ", "         ", "         ", "         ", "A       A", "         ", "         ").setRepeatable(6)
                .aisle("BB     BB", "A       A", "A       A", "A       A", "A       A", "A       A", "         ", "         ")
                .aisle("BB     BB", "         ", "         ", "         ", "         ", "A       A", "         ", "         ").setRepeatable(5)
                .aisle("BB     BB", "         ", "         ", "         ", "         ", "C       C", "D       D", "DDDDFDDDD")
                .aisle("BB     BB", "A       A", "A       A", "A       A", "A       A", "C       C", "D       D", "DDDDFDDDD")
                .aisle("BB     BB", "         ", "         ", "         ", "         ", "C       C", "D       D", "DDDDFDDDD")
                .aisle("BB     BB", "         ", "         ", "         ", "         ", "A       A", "         ", "         ").setRepeatable(5)
                .aisle("BB     BB", "A       A", "A       A", "A       A", "A       A", "A       A", "         ", "         ")
                .aisle("BB     BB", "         ", "         ", "         ", "         ", "A       A", "         ", "         ").setRepeatable(6)
                .aisle("BBBBSBBBB", "A       A", "A       A", "A       A", "A       A", "A       A", "         ", "         ")
                .where('S', selfPredicate())
                .where('A', states(new IBlockState[]{MetaBlocks.FRAMES.get(Materials.Steel).getBlock(Materials.Steel)}))
                .where('B', states(MetaBlocks.STONE_BLOCKS.get(StoneVariantBlock.StoneVariant.SMOOTH).getState(StoneVariantBlock.StoneType.CONCRETE_LIGHT))
                        .or(autoAbilities(true, true, true, false, true, true, false)))
                .where('C', states(new IBlockState[]{MetaBlocks.TURBINE_CASING.getState(BlockTurbineCasing.TurbineCasingType.STEEL_GEARBOX)}))
                .where('D', states(new IBlockState[]{MetaBlocks.METAL_CASING.getState(MetalCasingType.STEEL_SOLID)}))
                .where('F', states(new IBlockState[]{MetaBlocks.TURBINE_CASING.getState(BlockTurbineCasing.TurbineCasingType.STEEL_GEARBOX)})
                        .or(autoAbilities(false, false, false, true, false, false, false)))
                .where(' ', any())
                .build();
    }

    @Override
    public ICubeRenderer getBaseTexture(IMultiblockPart iMultiblockPart) {
        return Textures.SOLID_STEEL_CASING;
    }

    @Nonnull
    @Override
    protected ICubeRenderer getFrontOverlay() {
        return Textures.BLAST_FURNACE_OVERLAY;
    }
}
