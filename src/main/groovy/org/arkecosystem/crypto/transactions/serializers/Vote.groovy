package org.arkecosystem.crypto.transactions.serializers

import org.arkecosystem.crypto.transactions.Transaction
import org.arkecosystem.crypto.helpers.Base58
import java.nio.ByteBuffer
import static com.google.common.io.BaseEncoding.base16

class Vote extends AbstractSerializer {
    Vote(ByteBuffer buffer, Transaction transaction) {
        super(buffer, transaction)
    }

    void serialize() {
        List<String> votes = transaction.asset.votes

        for (int i = 0; i < votes.size(); i++) {
            votes[i] = (votes[i][0] == '+' ? '01' : '00') + votes[i].substring(1)
        }

        this.buffer.put transaction.asset.votes.size().byteValue()
        this.buffer.put base16().lowerCase().decode(votes.join(""))
    }
}
