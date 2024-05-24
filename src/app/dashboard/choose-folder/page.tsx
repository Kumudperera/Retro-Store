import { Button } from "@/components/ui/button";
import Link from "next/link";
import { Fragment } from "react";
import { navigationConfigs } from "../navigationConfigs";
import { PageProps } from "../../../../.next/types/app/layout";

export default function ChooseFolder(props: PageProps) {
  props.params.configs = navigationConfigs.chooseFolder;
  return (
    <Fragment>
      <div className="flex items-center">
        <h1 className="text-lg font-semibold md:text-2xl">Choose Folder</h1>
      </div>
      <div
        className="flex flex-1 items-center justify-center rounded-lg border border-dashed shadow-sm"
        x-chunk="dashboard-02-chunk-1"
      >
        <div className="flex flex-col items-center gap-1 text-center">
          <h3 className="text-2xl font-bold tracking-tight">
            You have no products
          </h3>
          <p className="text-sm text-muted-foreground">
            You can start selling as soon as you add a product.
          </p>
          <Button className="mt-4">
            <Link
              href={"/retro-store/dashboard"}
              className="mx-[-0.65rem] md:mx-0 flex items-center gap-4 md:gap-3 rounded-lg px-3 py-2 text-muted-foreground hover:text-primary"
            >
              Login
            </Link>
          </Button>
        </div>
      </div>
    </Fragment>
  );
}
