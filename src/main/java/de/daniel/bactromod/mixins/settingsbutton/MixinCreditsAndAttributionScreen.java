package de.daniel.bactromod.mixins.settingsbutton;

import de.daniel.bactromod.config.ConfigScreen;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.components.Button;
import net.minecraft.client.gui.layouts.LayoutElement;
import net.minecraft.client.gui.layouts.LinearLayout;
import net.minecraft.client.gui.screens.CreditsAndAttributionScreen;
import net.minecraft.network.chat.Component;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;

@Mixin(CreditsAndAttributionScreen.class)
public class MixinCreditsAndAttributionScreen {

    @Redirect(method = "init", at = @At(value = "INVOKE", target = "Lnet/minecraft/client/gui/layouts/LinearLayout;addChild(Lnet/minecraft/client/gui/layouts/LayoutElement;)Lnet/minecraft/client/gui/layouts/LayoutElement;", ordinal = 2))
    public <T extends LayoutElement> T addChildAlt(LinearLayout instance, T layoutElement) {
        CreditsAndAttributionScreen inst = ((CreditsAndAttributionScreen) (Object) this);
        instance.addChild(layoutElement, instance.newCellSettings());
        return (T) instance.addChild(Button.builder(Component.literal("BactroMod Settings"), (button) -> Minecraft.getInstance().setScreen(new ConfigScreen(inst))).width(210).build());
    }

}