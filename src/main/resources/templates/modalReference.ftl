<#--
                                        Modal with refence
-->
<@referenceModal />

<#macro referenceModal>
    <#-- Modal -->
    <div class="modal fade" id="modalFilterHelper" tabindex="-1" role="dialog" aria-labelledby="exampleModalCenterTitle"
         aria-hidden="true">
        <div class="modal-dialog modal-dialog-centered" role="document">
            <div class="modal-content">
                <div class="modal-header justify-content-center">
                    <h3 class="modal-title" id="referenceName">Modal title</h3>
                </div>
                <div class="modal-body">
                    <div class="tips" id="referenceDescription"></div>
                    <div class="tips parameters" id="referenceParameters"></div>
                    <div class="tips" id="referenceExample"></div>
                    <div class="tips" id="referenceOutput"></div>
                </div>
                <div class="modal-footer">
                    <button type="button" class="submit-btn" data-dismiss="modal" onclick="addReferenceToEditor()">Insert
                    </button>
                    <button type="button" class="cancel-btn" data-dismiss="modal">Cancel</button>
                </div>
            </div>
        </div>
    </div>
</#macro>