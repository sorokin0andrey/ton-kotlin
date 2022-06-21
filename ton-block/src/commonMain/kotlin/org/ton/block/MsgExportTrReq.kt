package org.ton.block

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import org.ton.cell.CellBuilder
import org.ton.cell.CellSlice
import org.ton.tlb.TlbConstructor
import org.ton.tlb.loadTlb
import org.ton.tlb.storeTlb

@Serializable
@SerialName("msg_export_tr_req")
data class MsgExportTrReq(
    val out_msg: MsgEnvelope,
    val imported: InMsg,
) : OutMsg {
    companion object {
        @JvmStatic
        fun tlbCodec(): TlbConstructor<MsgExportTrReq> = MsgExportTrReqTlbConstructor
    }
}

private object MsgExportTrReqTlbConstructor : TlbConstructor<MsgExportTrReq>(
    schema = "msg_export_tr_req\$111 out_msg:^MsgEnvelope imported:^InMsg = OutMsg;"
) {
    val msgEnvelope by lazy { MsgEnvelope.tlbCodec() }
    val inMsg by lazy { InMsg.tlbCodec() }

    override fun storeTlb(
        cellBuilder: CellBuilder,
        value: MsgExportTrReq
    ) = cellBuilder {
        storeRef { storeTlb(msgEnvelope, value.out_msg) }
        storeRef { storeTlb(inMsg, value.imported) }
    }

    override fun loadTlb(
        cellSlice: CellSlice
    ): MsgExportTrReq = cellSlice {
        val outMsg = loadRef { loadTlb(msgEnvelope) }
        val imported = loadRef { loadTlb(inMsg) }
        MsgExportTrReq(outMsg, imported)
    }
}