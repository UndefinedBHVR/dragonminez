package com.yuseix.dragonminez.datagen;

import com.yuseix.dragonminez.DragonMineZ;
import com.yuseix.dragonminez.init.MainBlocks;
import net.minecraft.core.HolderLookup;
import net.minecraft.data.PackOutput;
import net.minecraft.data.tags.ItemTagsProvider;
import net.minecraft.tags.ItemTags;
import net.minecraft.world.level.block.Block;
import net.minecraftforge.common.data.ExistingFileHelper;
import org.jetbrains.annotations.Nullable;

import java.util.concurrent.CompletableFuture;

public class DMZItemTagGenerator extends ItemTagsProvider {
    public DMZItemTagGenerator(PackOutput pOutput, CompletableFuture<HolderLookup.Provider> pLookupProvider,
                               CompletableFuture<TagLookup<Block>> pBlockTags, @Nullable ExistingFileHelper existingFileHelper) {
        super(pOutput, pLookupProvider, pBlockTags, DragonMineZ.MOD_ID, existingFileHelper);
    }

    @Override
    protected void addTags(HolderLookup.Provider pProvider) {
        this.tag(ItemTags.LOGS)
                .add(MainBlocks.NAMEK_AJISSA_LOG.get().asItem())
                .add(MainBlocks.NAMEK_AJISSA_WOOD.get().asItem())
                .add(MainBlocks.NAMEK_STRIPPED_AJISSA_LOG.get().asItem())
                .add(MainBlocks.NAMEK_STRIPPED_AJISSA_WOOD.get().asItem())
                .add(MainBlocks.NAMEK_SACRED_LOG.get().asItem())
                .add(MainBlocks.NAMEK_SACRED_WOOD.get().asItem())
                .add(MainBlocks.NAMEK_STRIPPED_SACRED_LOG.get().asItem())
                .add(MainBlocks.NAMEK_STRIPPED_SACRED_WOOD.get().asItem());

        this.tag(ItemTags.LOGS_THAT_BURN)
                .add(MainBlocks.NAMEK_AJISSA_LOG.get().asItem())
                .add(MainBlocks.NAMEK_AJISSA_WOOD.get().asItem())
                .add(MainBlocks.NAMEK_STRIPPED_AJISSA_LOG.get().asItem())
                .add(MainBlocks.NAMEK_STRIPPED_AJISSA_WOOD.get().asItem())
                .add(MainBlocks.NAMEK_SACRED_LOG.get().asItem())
                .add(MainBlocks.NAMEK_SACRED_WOOD.get().asItem())
                .add(MainBlocks.NAMEK_STRIPPED_SACRED_LOG.get().asItem())
                .add(MainBlocks.NAMEK_STRIPPED_SACRED_WOOD.get().asItem());

        this.tag(ItemTags.PLANKS)
                .add(MainBlocks.NAMEK_AJISSA_PLANKS.get().asItem())
                .add(MainBlocks.NAMEK_SACRED_PLANKS.get().asItem());

        this.tag(ItemTags.STONE_CRAFTING_MATERIALS)
                .add(MainBlocks.NAMEK_STONE.get().asItem())
                .add(MainBlocks.NAMEK_COBBLESTONE.get().asItem())
                .add(MainBlocks.NAMEK_DEEPSLATE.get().asItem());
    }
}
