/*
 * Licensed to ElasticSearch and Shay Banon under one
 * or more contributor license agreements.  See the NOTICE file
 * distributed with this work for additional information
 * regarding copyright ownership. ElasticSearch licenses this
 * file to you under the Apache License, Version 2.0 (the
 * "License"); you may not use this file except in compliance
 * with the License.  You may obtain a copy of the License at
 *
 *    http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing,
 * software distributed under the License is distributed on an
 * "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY
 * KIND, either express or implied.  See the License for the
 * specific language governing permissions and limitations
 * under the License.
 */

package org.elasticsearch.index.shard;

import org.elasticsearch.ElasticSearchIllegalArgumentException;

/**
 *
 */
public enum IndexShardState {
    CREATED((byte) 0),
    RECOVERING((byte) 1),
    POST_RECOVERY((byte) 2),
    STARTED((byte) 3),
    RELOCATED((byte) 4),
    CLOSED((byte) 5);

    private static final IndexShardState[] ORDS = new IndexShardState[IndexShardState.values().length];

    static {
        for (IndexShardState state : IndexShardState.values()) {
            ORDS[state.id()] = state;
        }
    }

    private final byte id;

    IndexShardState(byte id) {
        this.id = id;
    }

    public byte id() {
        return this.id;
    }

    public static IndexShardState fromId(byte id) throws ElasticSearchIllegalArgumentException {
        if (id < ORDS[0].id && id > ORDS[ORDS.length - 1].id) {
            throw new ElasticSearchIllegalArgumentException("No mapping for id [" + id + "]");
        }
        return ORDS[id];
    }
}
