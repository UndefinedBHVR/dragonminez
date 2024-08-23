package com.yuseix.dragonminez.init.items.custom;

import com.yuseix.dragonminez.init.MainEntity;
import com.yuseix.dragonminez.init.entity.custom.NubeEntity;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.context.UseOnContext;
import net.minecraft.world.level.Level;

public class KintonFlyingItem extends Item {
    public KintonFlyingItem( ) {
        super(new Properties().stacksTo(1));
    }

    @Override
    public InteractionResult useOn(UseOnContext pContext) {
        // Obtener el jugador y el nivel (mundo) del contexto
        Player player = pContext.getPlayer();
        Level level = pContext.getLevel();
        BlockPos pos = pContext.getClickedPos();
        Direction direction = pContext.getClickedFace();

        BlockPos spawnPos = pos.above(); // Posición encima del bloque clickeado

        // Verificar si el jugador y el mundo no son nulos
        if (player != null && level != null) {
            NubeEntity nube = new NubeEntity(MainEntity.NUBE_VOLADORA.get(), level); // Usa el tipo de entidad de nube

            nube.setPos(spawnPos.getX(), spawnPos.getY(), spawnPos.getZ());

            // Agregar la nube al mundo
            level.addFreshEntity(nube);

            pContext.getItemInHand().shrink(1);

            return InteractionResult.sidedSuccess(level.isClientSide);
        }

        return super.useOn(pContext);
    }
}
