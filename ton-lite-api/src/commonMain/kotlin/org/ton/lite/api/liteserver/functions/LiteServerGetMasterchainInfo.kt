package org.ton.lite.api.liteserver.functions

import io.ktor.utils.io.core.*
import org.ton.lite.api.liteserver.LiteServerMasterchainInfo
import org.ton.tl.TlConstructor

fun interface LiteServerGetMasterchainInfoFunction : LiteServerQueryFunction {
    suspend fun getMasterchainInfo(): LiteServerMasterchainInfo =
        query(LiteServerGetMasterchainInfo, LiteServerGetMasterchainInfo, LiteServerMasterchainInfo)
}

object LiteServerGetMasterchainInfo : TlConstructor<LiteServerGetMasterchainInfo>(
    type = LiteServerGetMasterchainInfo::class,
    schema = "liteServer.getMasterchainInfo = liteServer.MasterchainInfo"
) {
    override fun decode(input: Input): LiteServerGetMasterchainInfo = this

    override fun encode(output: Output, value: LiteServerGetMasterchainInfo) {
    }
}