import models.RendzuCommand

data class RendzuCtx(
    var command: RendzuCommand = RendzuCommand.NONE
)