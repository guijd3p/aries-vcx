package com.evernym.sdk.vcx.credentialDef;

import com.evernym.sdk.vcx.LibVcx;
import com.evernym.sdk.vcx.ParamGuard;
import com.evernym.sdk.vcx.VcxException;
import com.evernym.sdk.vcx.VcxJava;
import com.sun.jna.Callback;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.concurrent.CompletableFuture;

public class CredentialDefApi extends VcxJava.API {

    private static final Logger logger = LoggerFactory.getLogger("CredentialDefApi");
    private static Callback credentialDefCreateCB = new Callback() {
        // TODO: This callback and jna definition needs to be fixed for this API
        // it should accept connection handle as well
        @SuppressWarnings({"unused", "unchecked"})
        public void callback(int commandHandle, int err, int credentialDefHandle) {
            logger.debug("callback() called with: commandHandle = [" + commandHandle + "], err = [" + err + "], credentialDefHandle = [" + credentialDefHandle + "]");
            CompletableFuture<Integer> future = (CompletableFuture<Integer>) removeFuture(commandHandle);
            if (!checkCallback(future, err)) return;
            Integer result = credentialDefHandle;
            future.complete(result);
        }
    };

    private static Callback credentialDefSerializeCB = new Callback() {
        @SuppressWarnings({"unused", "unchecked"})
        public void callback(int commandHandle, int err, String serializedData) {
            logger.debug("callback() called with: commandHandle = [" + commandHandle + "], err = [" + err + "], serializedData = [****]");
            CompletableFuture<String> future = (CompletableFuture<String>) removeFuture(commandHandle);
            if (!checkCallback(future, err)) return;
            // TODO complete with exception if we find error
//            if (err != 0) {
//                future.completeExceptionally();
//            } else {
//
//            }
            String result = serializedData;
            future.complete(result);
        }
    };

    public static CompletableFuture<String> credentialDefSerialize(int credentialDefHandle) throws VcxException {
        ParamGuard.notNull(credentialDefHandle, "credentialDefHandle");
        logger.debug("credentialDefSerialize() called with: credentialDefHandle = [" + credentialDefHandle + "]");
        CompletableFuture<String> future = new CompletableFuture<>();
        int commandHandle = addFuture(future);

        int result = LibVcx.api.vcx_credentialdef_serialize(
                commandHandle,
                credentialDefHandle,
                credentialDefSerializeCB
        );
        checkResult(result);
        return future;
    }

    private static Callback credentialDefDeserialize = new Callback() {
        @SuppressWarnings({"unused", "unchecked"})
        public void callback(int commandHandle, int err, int credntialDefHandle) {
            logger.debug("callback() called with: commandHandle = [" + commandHandle + "], err = [" + err + "], credntialDefHandle = [" + credntialDefHandle + "]");
            CompletableFuture<Integer> future = (CompletableFuture<Integer>) removeFuture(commandHandle);
            if (!checkCallback(future, err)) return;
            // TODO complete with exception if we find error
//            if (err != 0) {
//                future.completeExceptionally();
//            } else {
//
//            }
            Integer result = credntialDefHandle;
            future.complete(result);
        }
    };

    public static CompletableFuture<Integer> credentialDefDeserialize(String credentialDefData) throws VcxException {
        ParamGuard.notNull(credentialDefData, "credentialDefData");
        logger.debug("credentialDefSerialize() called with: credentialDefData = [****]");
        CompletableFuture<Integer> future = new CompletableFuture<>();
        int commandHandle = addFuture(future);

        int result = LibVcx.api.vcx_credentialdef_deserialize(
                commandHandle,
                credentialDefData,
                credentialDefDeserialize
        );
        checkResult(result);
        return future;
    }


    private static Callback credentialDefGetCredentialDefIdCb = new Callback() {
        @SuppressWarnings({"unused", "unchecked"})
        public void callback(int commandHandle, int err, String credentialDefId) {
            logger.debug("callback() called with: commandHandle = [" + commandHandle + "], err = [" + err + "], credentialDefId = [" + credentialDefId + "]");
            CompletableFuture<String> future = (CompletableFuture<String>) removeFuture(commandHandle);
            if (!checkCallback(future, err)) return;
            future.complete(credentialDefId);
        }
    };

    public static CompletableFuture<String> credentialDefGetCredentialDefId(int credDefHandle) throws VcxException {
        ParamGuard.notNull(credDefHandle, "credDefHandle");
        logger.debug("credentialDefGetCredentialDefId() called with: credDefHandle = [" + credDefHandle + "]");
        CompletableFuture<String> future = new CompletableFuture<>();
        int commandHandle = addFuture(future);
        int result = LibVcx.api.vcx_credentialdef_get_cred_def_id(commandHandle,credDefHandle, credentialDefGetCredentialDefIdCb);
        checkResult(result);
        return future;
    }

    public static int credentialDefRelease(int handle) throws VcxException {
        ParamGuard.notNull(handle, "handle");
        logger.debug("credentialDefRelease() called with: handle = [" + handle + "]");

        int result = LibVcx.api.vcx_credentialdef_release(handle);
        checkResult(result);

        return result;
    }

	private static Callback vcxIntegerCB = new Callback() {
		@SuppressWarnings({"unused", "unchecked"})
		public void callback(int commandHandle, int err, int s) {
			logger.debug("callback() called with: commandHandle = [" + commandHandle + "], err = [" + err + "], s = [" + s + "]");
			CompletableFuture<Integer> future = (CompletableFuture<Integer>) removeFuture(commandHandle);
			if (! checkCallback(future, err)) return;
			Integer result = s;
			future.complete(result);
		}
	};

	public static CompletableFuture<Integer> credentialDefUpdateState(int handle) throws VcxException {
		logger.debug("vcxSchemaUpdateState() called with: handle = [" + handle + "]");
		CompletableFuture<Integer> future = new CompletableFuture<>();
		int commandHandle = addFuture(future);

		int result = LibVcx.api.vcx_credentialdef_update_state(
				commandHandle,
				handle,
				vcxIntegerCB
		);
		checkResult(result);
		return future;
	}

	public static CompletableFuture<Integer> credentialDefGetState(int handle) throws VcxException {
		logger.debug("schemaGetState() called with: handle = [" + handle + "]");
		CompletableFuture<Integer> future = new CompletableFuture<>();
		int commandHandle = addFuture(future);

		int result = LibVcx.api.vcx_credentialdef_get_state(
				commandHandle,
				handle,
				vcxIntegerCB
		);
		checkResult(result);
		return future;
	}
}
