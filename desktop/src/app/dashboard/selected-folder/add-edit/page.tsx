import SelectedFolderAddEdit from "../_components/SelectedFolderAddEdit";
import { SelectedFolderAddEditProvider } from "./_contexts/SelectedFolderAddEditProvider";

export default function AddEditSelectedFolder() {
  return (
    <SelectedFolderAddEditProvider>
    <SelectedFolderAddEdit />
    </SelectedFolderAddEditProvider>
  )
}
