"use client";
import { Button } from "@/components/ui/button";
import Link from "next/link";
import { PageProps } from "../../../../.next/types/app/layout";
import SelectedFolderSearch from "./_components/SelectedFolderSearch";
import SelectedFolderSearchResult from "./_components/SelectedFolderSearchResult";
import { SelectedFolderSearchProvider } from "./_contexts/SelectedFolderSearchProvider";
import Constants from "@/utils/constants";
import { Fragment } from "react";

export default function SelectedFolder(props: PageProps) {
  /* return (
    <SelectedFolderSearchProvider>
      <SelectedFolderBase />
    </SelectedFolderSearchProvider>
  ); */

  return (
    <Fragment>
      <div className="flex justify-between mt-2 w-full items-center">
        <div></div>
        <Button className="first-letter:capitalize">
          <Link
            href={Constants.PAGES.SELECTED_FOLDER_ADD_EDIT}
            className="mx-[-0.65rem] md:mx-0 flex items-center gap-4 md:gap-3 rounded-lg px-3 py-2 text-muted-foreground hover:text-primary"
          >
            Create New
          </Link>
        </Button>
      </div>

      <SelectedFolderSearchProvider>
        <div className="flex flex-col gap-10">
          <SelectedFolderSearch />

          <SelectedFolderSearchResult />
        </div>
      </SelectedFolderSearchProvider>
    </Fragment>
  );
}
